package nix.alevel.factory;

import nix.alevel.ConsoleHelperService;
import org.reflections.Reflections;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ConsoleFactory {
    private  static ConsoleFactory instance;
    private  Reflections reflections;
    private  Set<Class<? extends ConsoleHelperService>> consoleHelperService;
    private  Map<Class<? extends ConsoleHelperService>, Object> maps = new ConcurrentHashMap<Class<? extends ConsoleHelperService>, Object>();

    private ConsoleFactory() {
        reflections = new Reflections("nix.alevel");
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
        for (Class<? extends ConsoleHelperService> consoleService : consoleHelperService) {
            if (!consoleService.isAnnotationPresent(Deprecated.class)) {
                try {
                    return consoleService.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException("RuntimeException is occurred");
                }
            }
        }
        throw new RuntimeException("RuntimeException is occurred");
    }
}
