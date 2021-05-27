package util;

import annotation.PropertyKey;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class PropertyHandler {
    public static <T> T initPropertyObject(Properties properties, Class<T> tClass) {
        try {
            Constructor<T> constructor = tClass.getDeclaredConstructor();
            T instance = constructor.newInstance();
            for (Field field : tClass.getDeclaredFields()) {
                field.setAccessible(true);
                PropertyKey key = field.getAnnotation(PropertyKey.class);
                if (key == null) continue;
                String value = properties.getProperty(key.value());

                Class<?> fieldType = field.getType();
                try {
                    if (fieldType.equals(String.class)) {
                        field.set(instance, value);
                    } else if (fieldType.equals(Integer.class) || fieldType.equals(int.class)) {
                        field.setInt(instance, Integer.parseInt(value));
                    } else if (fieldType.equals(Double.class) || fieldType.equals(double.class)) {
                        field.setDouble(instance, Double.parseDouble(value));
                    } else if (fieldType.equals(Float.class) || fieldType.equals(float.class)) {
                        field.setDouble(instance, Float.parseFloat(value));
                    } else if (fieldType.equals(Short.class) || fieldType.equals(short.class)) {
                        field.setDouble(instance, Short.parseShort(value));
                    } else if (fieldType.equals(Byte.class) || fieldType.equals(byte.class)) {
                        field.setDouble(instance, Byte.parseByte(value));
                    } else if (fieldType.equals(Boolean.class) || fieldType.equals(boolean.class)) {
                        field.setBoolean(instance, Boolean.parseBoolean(value));
                    } else if (fieldType.equals(Long.class) || fieldType.equals(long.class)) {
                        field.setLong(instance, Long.parseLong(value));
                    } else if (fieldType.isEnum()) {
                        Object[] arr = fieldType.getEnumConstants();
                        for (Object a : arr) {
                            if (String.valueOf(a).equalsIgnoreCase(value)) {
                                field.set(instance, a);
                                break;
                            }
                        }
                    }
                }catch (NumberFormatException exception){
                    System.out.println("Check the correctness of types in file.properties");
                }
            }
            return instance;
        } catch (InvocationTargetException | IllegalAccessException | InstantiationException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }
}
