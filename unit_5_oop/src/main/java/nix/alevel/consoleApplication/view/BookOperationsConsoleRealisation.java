package nix.alevel.consoleApplication.view;

import nix.alevel.dao.impl.AuthorDAOImpl;
import nix.alevel.dao.impl.BookDAOImpl;
import nix.alevel.dao.AuthorDAO;
import nix.alevel.dao.BookDAO;
import nix.alevel.entity.Author;
import nix.alevel.entity.Book;
import nix.alevel.consoleApplication.util.consolehelper.ConsoleHelper;

import java.io.IOException;
import java.util.List;

public class BookOperationsConsoleRealisation {
    private static final BookDAO BOOK_DAO = new BookDAOImpl();
    private static final AuthorDAO AUTHOR_DAO = new AuthorDAOImpl();
    private static final ConsoleHelper consoleHelper = ConsoleHelper.getInstance();

    public static void createBook(){
        Book book = new Book();
        String name = enterBookName();
        book.setName(name);
        try {
            BOOK_DAO.create(book);
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    public static void updateBook(){
        String currentBookName = enterBookName();
        try {
            Book bookToUpdate = BOOK_DAO.getByName(currentBookName);
            boolean t = true;
            while (t){
                System.out.println("1. Update Name"+
                        "\n2. Add author to book" +
                        "\n3. Delete author" +
                        "\n0. Exit");

                switch (consoleHelper.readInteger()){
                    case 1:{
                        String name = enterBookName();
                        try {
                            bookToUpdate.setName(name);
                            BOOK_DAO.update(bookToUpdate.getId());
                        }catch (RuntimeException e){
                            System.out.println(e.getMessage());
                        }
                        break;
                    }

                    case 2:{
                        System.out.println("Enter author name: ");
                        String[] authorFirstLastNames = AuthorOperationsConsoleRealisation.enterFirstLastName();
                        try {
                            BOOK_DAO.addAuthor(bookToUpdate.getId(), (AUTHOR_DAO.getByName(authorFirstLastNames[0], authorFirstLastNames[1]).getId()));
                            AUTHOR_DAO.addBook(AUTHOR_DAO.getByName(authorFirstLastNames[0], authorFirstLastNames[1]).getId(),bookToUpdate.getId());
                        } catch (RuntimeException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    }
                    case 3:{
                        System.out.println("Enter author name: ");
                        String[] authorFirstLastNames = AuthorOperationsConsoleRealisation.enterFirstLastName();
                        try {
                            BOOK_DAO.deleteAuthor(bookToUpdate.getId(), (AUTHOR_DAO.getByName(authorFirstLastNames[0], authorFirstLastNames[1]).getId()));
                            AUTHOR_DAO.deleteBook( AUTHOR_DAO.getByName(authorFirstLastNames[0], authorFirstLastNames[1]).getId(), bookToUpdate.getId());
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

    }
    public static void printAllBoos(){
        try {
            for (Book book: BOOK_DAO.readAll()){
                System.out.println(BOOK_DAO.printBook(book));
            }
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void printBookByName(){
        String bookName = enterBookName();
        try {
            Book book = BOOK_DAO.getByName(bookName);
            System.out.println(BOOK_DAO.printBook(book));
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    public static void printBooksByAuthorName(){
        String[] authorFirstLastNames = AuthorOperationsConsoleRealisation.enterFirstLastName();
        try{
            List<Book> list = BOOK_DAO.getByAuthorName(authorFirstLastNames[0], authorFirstLastNames[1]);
            for (Book a : list)
                BOOK_DAO.printBook(a);

        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    public static void deleteBookFromDB(){
        String bookName = enterBookName();
        try {
            Book book = BOOK_DAO.getByName(bookName);
            List<String> authors = book.getAuthors();
            for (int i= 0; i<authors.size(); i++){
                AUTHOR_DAO.deleteBook(authors.get(i), book.getId());
            }
            BOOK_DAO.delete(BOOK_DAO.getByName(bookName).getId());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
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
