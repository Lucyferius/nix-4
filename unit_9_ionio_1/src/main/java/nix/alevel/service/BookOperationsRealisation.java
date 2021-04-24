package nix.alevel.service;

import nix.alevel.dao.impl.AuthorDAOImpl;
import nix.alevel.dao.impl.BookDAOImpl;
import nix.alevel.dao.AuthorDAO;
import nix.alevel.dao.BookDAO;
import nix.alevel.entity.Book;
import nix.alevel.consoleApplicationController.util.consolehelper.ConsoleHelper;

import java.util.List;

public class BookOperationsRealisation {
    private static final BookDAO BOOK_DAO = new BookDAOImpl();
    private static final AuthorDAO AUTHOR_DAO = new AuthorDAOImpl();
    private static final ConsoleHelper consoleHelper = ConsoleHelper.getInstance();

    public static void createBook(String name){
        Book book = new Book();
        book.setName(name);
        try {
            BOOK_DAO.create(book);
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }
    public static void updateBookName(String currentBookName, String newName){
        try {
            Book bookToUpdate = BOOK_DAO.getByName(currentBookName);
            bookToUpdate.setName(newName);
            System.out.println("bookToUpdate = " + bookToUpdate.getName());
            BOOK_DAO.update(bookToUpdate);
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }
    public static void addAuthorToBook(String currentBookName, String[] authorFirstLastNames){
        try {
            Book bookToUpdate = BOOK_DAO.getByName(currentBookName);
            if(!bookToUpdate.getAuthors().contains(AUTHOR_DAO.getByName(authorFirstLastNames[0], authorFirstLastNames[1]).getId())) {
                BOOK_DAO.addAuthor(bookToUpdate.getId(), (AUTHOR_DAO.getByName(authorFirstLastNames[0], authorFirstLastNames[1]).getId()));
                AUTHOR_DAO.addBook(AUTHOR_DAO.getByName(authorFirstLastNames[0], authorFirstLastNames[1]).getId(), bookToUpdate.getId());
            }else System.out.println("Book already has this author.");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void deleteBookFromAuthor(String currentBookName, String[] authorFirstLastNames){
        try {
            Book bookToUpdate = BOOK_DAO.getByName(currentBookName);
            BOOK_DAO.deleteAuthor(bookToUpdate.getId(), (AUTHOR_DAO.getByName(authorFirstLastNames[0], authorFirstLastNames[1]).getId()));
            AUTHOR_DAO.deleteBook( AUTHOR_DAO.getByName(authorFirstLastNames[0], authorFirstLastNames[1]).getId(), bookToUpdate.getId());
        } catch (RuntimeException e) {
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

    public static void printBookByName(String bookName){
        try {
            Book book = BOOK_DAO.getByName(bookName);
            System.out.println(BOOK_DAO.printBook(book));
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    public static void printBooksByAuthorName(String[] authorFirstLastNames ){
        try{
            List<Book> list = BOOK_DAO.getByAuthorName(authorFirstLastNames[0], authorFirstLastNames[1]);
            for (Book a : list)
                System.out.println(BOOK_DAO.printBook(a));

        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    public static void deleteBookFromDB(String bookName){
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
}