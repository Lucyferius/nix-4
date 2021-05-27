package util;

import annotation.CsvCell;
import model.CsvTable;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class CsvMapper {
    public static <T> List<T> format(CsvTable csvTable, Class<T> tClass) {
        List<T> res = new ArrayList<>();
        try {
            Constructor<T> constructor = tClass.getConstructor();
            List<List<String>> rows = csvTable.getRows();
            Field[] fields = tClass.getDeclaredFields();
            List<Field> annotationFields = new ArrayList<>();
            List<String> columns = new ArrayList<>();

            for (Field someField : fields) {
                CsvCell cell = someField.getAnnotation(CsvCell.class);
                if(cell!=null) {
                    columns.add(cell.value());
                    someField.setAccessible(true);
                    annotationFields.add(someField);
                }
            }

            for (List<String> row : rows) {
                T instance = constructor.newInstance();
                try {
                    for (int i = 0; i < columns.size(); i++) {
                        String columnName = columns.get(i);
                        Field field = annotationFields.get(i);
                        int index = csvTable.getHeaderColumnIndex(columnName);
                        String cell = row.get(index);

                        Class<?> fieldType = field.getType();
                        if (fieldType.equals(String.class)) {
                            field.set(instance, cell);
                        } else if (fieldType.equals(Integer.class) || fieldType.equals(int.class)) {
                            field.setInt(instance, Integer.parseInt(cell));
                        } else if (fieldType.equals(Double.class) || fieldType.equals(double.class)) {
                            field.setDouble(instance, Double.parseDouble(cell));
                        } else if (fieldType.equals(Float.class) || fieldType.equals(float.class)) {
                            field.setDouble(instance, Float.parseFloat(cell));
                        } else if (fieldType.equals(Short.class) || fieldType.equals(short.class)) {
                            field.setDouble(instance, Short.parseShort(cell));
                        } else if (fieldType.equals(Byte.class) || fieldType.equals(byte.class)) {
                            field.setDouble(instance, Byte.parseByte(cell));
                        } else if (fieldType.equals(Boolean.class) || fieldType.equals(boolean.class)) {
                            field.setBoolean(instance, Boolean.parseBoolean(cell));
                        } else if (fieldType.equals(Long.class) || fieldType.equals(long.class)) {
                            field.setLong(instance, Long.parseLong(cell));
                        } else if (fieldType.isEnum()) {
                            Object[] arr = fieldType.getEnumConstants();
                            for (Object a : arr) {
                                if (String.valueOf(a).equalsIgnoreCase(cell)) {
                                    field.set(instance, a);
                                    break;
                                }
                            }
                        }
                    }
                    res.add(instance);
                } catch (NumberFormatException e) {
                    System.out.println(e.getMessage() + "Check the correctness of your file");
                }
            }
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            System.out.println(e.getMessage());
        }
        return res;
    }
}
