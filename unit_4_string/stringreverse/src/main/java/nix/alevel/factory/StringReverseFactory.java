package nix.alevel.factory;

import nix.alevel.StringReverseService;
import org.reflections.Reflections;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class StringReverseFactory {
    private  static StringReverseFactory instance;
    private Reflections reflections;
    private Set<Class<? extends StringReverseService>> stringReverseService;
    private Map<Class<? extends StringReverseService>, Object> maps = new ConcurrentHashMap<Class<? extends StringReverseService>, Object>();

    private StringReverseFactory() {
        reflections = new Reflections("nix.alevel");
        stringReverseService = reflections.getSubTypesOf(StringReverseService.class);
    }

    public static StringReverseService create() {
        return getInstance().getConsoleService();
    }

    private static StringReverseFactory getInstance() {
        if (instance == null) {
            instance = new StringReverseFactory();
        }
        return instance;
    }

    public  void printMap() {
        for (Map.Entry<Class<? extends StringReverseService>, Object> entry : maps.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    private  StringReverseService getConsoleService() {
        for (Class<? extends StringReverseService> stringService : stringReverseService) {
            if (!stringService.isAnnotationPresent(Deprecated.class)) {
                try {
                    return stringService.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException("RuntimeException is occurred");
                }
            }
        }
        throw new RuntimeException("RuntimeException is occurred");
    }
}
