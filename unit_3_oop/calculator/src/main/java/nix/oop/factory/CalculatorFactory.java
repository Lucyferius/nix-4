package nix.oop.factory;

import nix.oop.CalculatorService;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class CalculatorFactory {
    private static CalculatorFactory instance;
    private  Reflections reflections;
    private  Set<Class<? extends CalculatorService>> calcServices;
    private  Map<Class<? extends CalculatorService>, Object> maps = new ConcurrentHashMap<Class<? extends CalculatorService>, Object>();

    private CalculatorFactory() {
        reflections = new Reflections("nix.oop");
        calcServices = reflections.getSubTypesOf(CalculatorService.class);
    }
    public static CalculatorService create(){
        return  getInstance().getCalcService();
    }

    private static CalculatorFactory getInstance() {
        if (instance == null) {
            instance = new CalculatorFactory();
        }
        return instance;
    }
    public  void printMap(){
        for (Map.Entry<Class<? extends CalculatorService>, Object> entry : maps.entrySet()) {
            System.out.println(entry.getKey()+" : "+entry.getValue());
        }
    }

    private CalculatorService getCalcService() {
        Object obj;
        for (Class<? extends CalculatorService> calcService : calcServices) {
            if (!calcService.isAnnotationPresent(Deprecated.class)) {
                try {
                    return calcService.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException("Library usage error!");
                }
               /* obj = null;
                if(maps.containsKey(calcService)) continue;
                else{
                    try {
                        obj = calcService.getDeclaredConstructor().newInstance();
                        maps.put(calcService, obj);

                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    return (CalculatorService)maps.get(calcService);
                } catch (Exception e) {
                    throw new RuntimeException("RuntimeException is occurred");
                }*/
            }
        }
        throw new RuntimeException("RuntimeException is occurred");
    }
}
