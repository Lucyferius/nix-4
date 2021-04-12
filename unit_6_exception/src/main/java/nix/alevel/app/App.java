package nix.alevel.app;

import nix.alevel.controller.MainController;

public class App {
    public static void main(String[] args) {
        try {
            MainController.run();
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }
}
