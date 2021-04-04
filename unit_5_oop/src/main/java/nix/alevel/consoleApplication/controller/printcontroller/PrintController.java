package nix.alevel.consoleApplication.controller.printcontroller;

import nix.alevel.consoleApplication.util.consolehelper.ConsoleHelper;
import nix.alevel.consoleApplication.view.AuthorOperationsConsoleRealisation;
import nix.alevel.consoleApplication.view.BookOperationsConsoleRealisation;

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
                    AuthorOperationsConsoleRealisation.printAllAuthors();
                    System.out.println();
                    break;
                case 2:
                    AuthorOperationsConsoleRealisation.printAuthorByName();
                    System.out.println();
                    break;
                case 3:
                    BookOperationsConsoleRealisation.printAllBoos();
                    System.out.println();
                    break;
                case 4:
                    BookOperationsConsoleRealisation.printBookByName();
                    System.out.println();
                    break;
                case 5:
                    BookOperationsConsoleRealisation.printBooksByAuthorName();
                    System.out.println();
                    break;
                case 6:
                    AuthorOperationsConsoleRealisation.printAuthorsByBookName();
                    System.out.println();
                    break;
                case 0:
                    printController = false;
            }


        }
    }
}
