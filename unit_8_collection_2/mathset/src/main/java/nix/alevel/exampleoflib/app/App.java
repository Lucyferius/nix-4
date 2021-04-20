package nix.alevel.exampleoflib.app;

import nix.alevel.exampleoflib.controller.AppController;

import java.io.IOException;

public class App {
    public static void main(String[] args) {
        try {
            AppController.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
