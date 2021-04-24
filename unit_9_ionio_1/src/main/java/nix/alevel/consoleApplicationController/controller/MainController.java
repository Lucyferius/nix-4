package nix.alevel.consoleApplicationController.controller;

import nix.alevel.consoleApplicationController.controller.printcontroller.PrintController;
import nix.alevel.consoleApplicationController.util.consolehelper.ConsoleHelper;
import nix.alevel.service.AuthorOperationRealisation;
import nix.alevel.service.BookOperationsRealisation;

import java.io.IOException;

public class MainController {
    private final static ConsoleHelper consoleHelper = ConsoleHelper.getInstance();
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
                    AuthorOperationRealisation.createAuthor(enterFirstLastName());
                    break;

                case 2:
                    BookOperationsRealisation.createBook(enterBookName());
                    break;

                case 3:
                    try {
                        boolean t = true;
                        while (t) {
                            System.out.println("1. Update firstName" +
                                    "\n2. Update lastName" +
                                    "\n3. Add book to author" +
                                    "\n4. Delete book" +
                                    "\n0. Exit");

                            switch (consoleHelper.readInteger()) {
                                case 1: {
                                    String[] firstLastName = enterFirstLastName();
                                    System.out.print("New first name : ");
                                    String fN = "";
                                    try {
                                        fN = consoleHelper.readString();
                                    } catch (IOException e) {
                                        System.out.println(e.getMessage());
                                    }
                                    try {
                                        AuthorOperationRealisation.updateAuthorFirstName(firstLastName, fN);
                                    } catch (RuntimeException e) {
                                        System.out.println(e.getMessage());
                                    }
                                    break;
                                }
                                case 2: {
                                    String[] firstLastName = enterFirstLastName();
                                    System.out.print("New last name: ");
                                    String lN = "";
                                    try {
                                        lN = consoleHelper.readString();
                                    } catch (IOException e) {
                                        System.out.println(e.getMessage());
                                    }
                                    try {
                                        AuthorOperationRealisation.updateAuthorLastName(firstLastName, lN);
                                    } catch (RuntimeException e) {
                                        System.out.println(e.getMessage());
                                    }
                                    break;
                                }
                                case 3: {
                                    String[] firstLastName = enterFirstLastName();
                                    System.out.println("Enter book name (book must already exist): ");
                                    String bookName = enterBookName();
                                    try {
                                        AuthorOperationRealisation.addBookToAuthor(firstLastName, bookName);
                                    } catch (RuntimeException e) {
                                        System.out.println(e.getMessage());
                                    }
                                    break;
                                }
                                case 4: {
                                    String[] firstLastName = enterFirstLastName();
                                    System.out.println("Enter book name (book must already exist): ");
                                    String bookName = enterBookName();
                                    try {
                                        AuthorOperationRealisation.deleteBookFromAuthor(firstLastName, bookName);
                                    } catch (RuntimeException e) {
                                        System.out.println(e.getMessage());
                                    }
                                    break;
                                }
                                case 0:
                                    t = false;
                            }
                        }
                    }catch (RuntimeException e){
                        System.out.println(e.getMessage());
                    }
                    break;

                case 4:
                    try {
                        boolean t = true;
                        while (t){
                            System.out.println("1. Update Name"+
                                    "\n2. Add author to book" +
                                    "\n3. Delete author" +
                                    "\n0. Exit");

                            switch (consoleHelper.readInteger()){
                                case 1:{
                                    String currName = enterBookName();
                                    String name = enterBookName();
                                    try {
                                        BookOperationsRealisation.updateBookName(currName, name);
                                    }catch (RuntimeException e){
                                        System.out.println(e.getMessage());
                                    }
                                    break;
                                }

                                case 2:{
                                    String currName = enterBookName();
                                    System.out.println("Enter author name: ");
                                    String[] authorFirstLastNames = enterFirstLastName();
                                    try {
                                        BookOperationsRealisation.addAuthorToBook(currName, authorFirstLastNames);
                                    } catch (RuntimeException e) {
                                        System.out.println(e.getMessage());
                                    }
                                    break;
                                }
                                case 3:{
                                    String currName = enterBookName();
                                    System.out.println("Enter author name: ");
                                    String[] authorFirstLastNames = enterFirstLastName();
                                    try {
                                        BookOperationsRealisation.deleteBookFromAuthor(currName, authorFirstLastNames);
                                    } catch (RuntimeException e) {
                                        System.out.println(e.getMessage());
                                    }
                                    break;
                                }
                                case 0: t = false;
                            }
                        }
                    }catch (RuntimeException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    PrintController.run();
                    break;
                case 6:
                    AuthorOperationRealisation.deleteAuthorFromDB(enterFirstLastName());
                    break;
                case 7:
                    BookOperationsRealisation.deleteBookFromDB(enterBookName());
                    break;
                case 0:
                    System.exit(0);
            }
        }
    }
    public static String[] enterFirstLastName(){
        String[] firstLastName = new String[2];
        int check = 0;
        while(check == 0) {
            try {
                System.out.print("First name: ");
                firstLastName[0] = consoleHelper.readString();
                System.out.print("Last name: ");
                firstLastName[1] = consoleHelper.readString();
                if(!firstLastName[0].isBlank() && !firstLastName[1].isBlank()) {
                    check = 1;
                    return firstLastName;
                }
            } catch (IOException e) {
                System.out.println("enter the names again");
            }
        }
        throw new RuntimeException();
    }
    public static String enterBookName(){
        String bookName;
        int check = 0;
        while(check == 0) {
            try {
                System.out.print("BookName: ");
                bookName = consoleHelper.readString();
                if(!bookName.isBlank()) {
                    check = 1;
                    return bookName;
                }
            } catch (IOException e) {
                System.out.println("enter the names again");
            }
        }
        throw new RuntimeException();
    }
}
