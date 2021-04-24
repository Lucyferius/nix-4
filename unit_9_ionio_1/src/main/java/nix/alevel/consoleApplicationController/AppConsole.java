package nix.alevel.consoleApplicationController;

import nix.alevel.consoleApplicationController.controller.MainController;

public class AppConsole {
    public static void main(String[] args) {
        MainController controller = new MainController();
        try {
            controller.run();
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }
}
