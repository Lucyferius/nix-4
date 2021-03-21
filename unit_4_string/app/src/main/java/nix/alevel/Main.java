package nix.alevel;

import nix.alevel.application.Application;
import nix.alevel.impl.DefaultStringReverseService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Application application = new Application();
        try {
            application.run();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
