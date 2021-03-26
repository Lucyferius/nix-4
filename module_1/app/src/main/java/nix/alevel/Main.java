package nix.alevel;

import nix.alevel.controller.AppController;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            AppController appController = new AppController();
            appController.run();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
