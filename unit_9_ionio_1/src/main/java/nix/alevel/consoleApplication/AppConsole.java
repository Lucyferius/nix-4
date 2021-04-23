package nix.alevel.consoleApplication;

import nix.alevel.consoleApplication.controller.MainController;
import nix.alevel.dao.impl.BookDAOImpl;
import nix.alevel.entity.Book;

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
