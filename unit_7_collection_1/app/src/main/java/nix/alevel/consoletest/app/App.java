package nix.alevel.consoletest.app;

import nix.alevel.consoletest.controller.AppController;

import java.io.IOException;

public class App {
    public static void main(String[] args) {
        try {
            AppController appController = new AppController();
            appController.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
