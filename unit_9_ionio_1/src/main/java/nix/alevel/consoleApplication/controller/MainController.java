package nix.alevel.consoleApplication.controller;

import nix.alevel.consoleApplication.controller.printcontroller.PrintController;
import nix.alevel.consoleApplication.util.consolehelper.ConsoleHelper;
import nix.alevel.consoleApplication.view.AuthorOperationsConsoleRealisation;
import nix.alevel.consoleApplication.view.BookOperationsConsoleRealisation;

public class MainController {
    public void run(){
        boolean mainController = true;
        while (true){
            System.out.println("1. Create author\n" +
                    "2. Create book\n" +
                    "3. Update author\n" +
                    "4. Update book\n" +
                    "5. Print\n" +
                    "6. Delete author\n" +
                    "7. Delete book\n" +
                    "0. Exit");
            switch (ConsoleHelper.getInstance().readInteger()){
                case 1:
                    AuthorOperationsConsoleRealisation.createAuthor();
                    break;

                case 2:
                    BookOperationsConsoleRealisation.createBook();
                    break;

                case 3:
                    AuthorOperationsConsoleRealisation.updateAuthorByName();
                    break;

                case 4:
                    BookOperationsConsoleRealisation.updateBook();
                    break;
                case 5:
                    PrintController.run();
                    break;
                case 6:
                    AuthorOperationsConsoleRealisation.deleteAuthorFromDB();
                    break;
                case 7:
                    BookOperationsConsoleRealisation.deleteBookFromDB();
                    break;
                case 0:
                    System.exit(0);
            }
        }
    }
}
