package nix.oop.factory;

import nix.oop.ConsoleHelperService;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ConsoleFactory {
    private  static ConsoleFactory instance;
    private  Reflections reflections;
    private  Set<Class<? extends ConsoleHelperService>> consoleHelperService;
    private  Map<Class<? extends ConsoleHelperService>, Object> maps = new ConcurrentHashMap<Class<? extends ConsoleHelperService>, Object>();

    private ConsoleFactory() {
        reflections = new Reflections("nix.oop");
        consoleHelperService = reflections.getSubTypesOf(ConsoleHelperService.class);
    }

    public static ConsoleHelperService create() {
        return getInstance().getConsoleService();
    }

    private static ConsoleFactory getInstance() {
        if (instance == null) {
            instance = new ConsoleFactory();
        }
        return instance;
    }

    public  void printMap() {
        for (Map.Entry<Class<? extends ConsoleHelperService>, Object> entry : maps.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    private  ConsoleHelperService getConsoleService() {
        //ConsoleHelperService obj;
        for (Class<? extends ConsoleHelperService> consoleService : consoleHelperService) {
            //obj = null;
            if (!consoleService.isAnnotationPresent(Deprecated.class)) {
               /*if (maps.containsKey(consoleService)) continue;
                else {
                    try {
                        obj = consoleService.getDeclaredConstructor().newInstance();
                        maps.put(consoleService, obj);
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                }*/
                try {
                    return consoleService.getDeclaredConstructor().newInstance();
                    //return (ConsoleHelperService) maps.get(consoleService);
                } catch (Exception e) {
                    throw new RuntimeException("RuntimeException is occurred");
                }
            }
        }
        throw new RuntimeException("RuntimeException is occurred");
    }
}
