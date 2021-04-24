package nix.alevel.consoleApplicationController.controller.printcontroller;

import nix.alevel.consoleApplicationController.controller.MainController;
import nix.alevel.consoleApplicationController.util.consolehelper.ConsoleHelper;
import nix.alevel.service.AuthorOperationRealisation;
import nix.alevel.service.BookOperationsRealisation;

public class PrintController {
    public static void run(){
        ConsoleHelper consoleHelper = ConsoleHelper.getInstance();
        boolean printController = true;
        while (printController){
            System.out.println("\t1. Print all authors\n" +
                    "\t2. Print author by name\n" +
                    "\t3. Print all books\n" +
                    "\t4. Print book by name\n" +
                    "\t5. Print books  by author\n" +
                    "\t6. Print authors by book\n" +
                    "\t0. Exit");
            switch (consoleHelper.readInteger()){
                case 1:
                    AuthorOperationRealisation.printAllAuthors();
                    System.out.println();
                    break;
                case 2:
                    AuthorOperationRealisation.printAuthorByName(MainController.enterFirstLastName());
                    System.out.println();
                    break;
                case 3:
                    BookOperationsRealisation.printAllBoos();
                    System.out.println();
                    break;
                case 4:
                    BookOperationsRealisation.printBookByName(MainController.enterBookName());
                    System.out.println();
                    break;
                case 5:
                    BookOperationsRealisation.printBooksByAuthorName(MainController.enterFirstLastName());
                    System.out.println();
                    break;
                case 6:
                    AuthorOperationRealisation.printAuthorsByBookName(MainController.enterBookName());
                    System.out.println();
                    break;
                case 0:
                    printController = false;
            }


        }
    }
}
