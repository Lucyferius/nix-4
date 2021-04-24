package nix.alevel.service;

import nix.alevel.dao.impl.AuthorDAOImpl;
import nix.alevel.dao.impl.BookDAOImpl;
import nix.alevel.dao.AuthorDAO;
import nix.alevel.dao.BookDAO;
import nix.alevel.entity.Author;
import nix.alevel.consoleApplicationController.util.consolehelper.ConsoleHelper;

import java.util.List;

public class AuthorOperationRealisation {
    private final static BookDAO BOOK_DAO = new BookDAOImpl();
    private final static AuthorDAO AUTHOR_DAO = new AuthorDAOImpl();
    private final static ConsoleHelper consoleHelper = ConsoleHelper.getInstance();

    public static void createAuthor(String[] firstLastName) {
        Author author= new Author();
        author.setFirstName(firstLastName[0]);
        author.setLastName(firstLastName[1]);
        try {
            AUTHOR_DAO.create(author);
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }
    public static void updateAuthorFirstName(String[] firstLastName , String fN){
        try {
            Author authorToUpdate = AUTHOR_DAO.getByName(firstLastName[0], firstLastName[1]);
            authorToUpdate.setFirstName(fN);
            AUTHOR_DAO.update(authorToUpdate);
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }
    public static void updateAuthorLastName(String[] firstLastName , String lN){
        try {
            Author authorToUpdate = AUTHOR_DAO.getByName(firstLastName[0], firstLastName[1]);
            authorToUpdate.setLastName(lN);
            AUTHOR_DAO.update(authorToUpdate);
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }
    public static void addBookToAuthor(String[] firstLastName, String bookName){
        try {
            Author authorToUpdate = AUTHOR_DAO.getByName(firstLastName[0], firstLastName[1]);
            if(!AUTHOR_DAO.getByName(firstLastName[0], firstLastName[1]).getBookList().contains(BOOK_DAO.getByName(bookName).getId())) {
                AUTHOR_DAO.addBook(authorToUpdate.getId(), BOOK_DAO.getByName(bookName).getId());
                BOOK_DAO.addAuthor(BOOK_DAO.getByName(bookName).getId(), authorToUpdate.getId());
            }else System.out.println("Author already has this book.");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void deleteBookFromAuthor(String[] firstLastName, String bookName){
        try {
            Author authorToUpdate = AUTHOR_DAO.getByName(firstLastName[0], firstLastName[1]);
            AUTHOR_DAO.deleteBook(authorToUpdate.getId(), BOOK_DAO.getByName(bookName).getId());
            BOOK_DAO.deleteAuthor(BOOK_DAO.getByName(bookName).getId(), authorToUpdate.getId());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void printAllAuthors(){
        try {
            for (Author author: AUTHOR_DAO.readAll()){
                System.out.println(AUTHOR_DAO.printAuthor(author));
            }
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void printAuthorByName(String[] firstLastName){
        try {
            Author authorToUpdate = AUTHOR_DAO.getByName(firstLastName[0], firstLastName[1]);
            System.out.println(AUTHOR_DAO.printAuthor(authorToUpdate));
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    public static void printAuthorsByBookName(String bookName){
        try{
            List<Author> list = AUTHOR_DAO.getByBookName(bookName);
            for (Author a : list)
                System.out.println(AUTHOR_DAO.printAuthor(a));

        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    public static void deleteAuthorFromDB(String[] firstLastName){
        try {
            Author author = AUTHOR_DAO.getByName(firstLastName[0], firstLastName[1]);
            List<String> books = author.getBookList();
            for(int i =0 ; i<books.size(); i++){
                BOOK_DAO.deleteAuthor(books.get(i), author.getId());
            }
            AUTHOR_DAO.delete(author.getId());
        } catch (RuntimeException e) {
            System.out.println("Wrong name");
        }
    }


}