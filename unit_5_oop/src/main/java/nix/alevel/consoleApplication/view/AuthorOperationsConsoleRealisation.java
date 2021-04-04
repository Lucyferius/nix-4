package nix.alevel.consoleApplication.view;

import nix.alevel.dao.impl.AuthorDAOImpl;
import nix.alevel.dao.impl.BookDAOImpl;
import nix.alevel.dao.AuthorDAO;
import nix.alevel.dao.BookDAO;
import nix.alevel.entity.Author;
import nix.alevel.consoleApplication.util.consolehelper.ConsoleHelper;
import nix.alevel.entity.Book;

import java.io.IOException;
import java.util.List;

public class AuthorOperationsConsoleRealisation {
    private final static BookDAO BOOK_DAO = new BookDAOImpl();
    private final static AuthorDAO AUTHOR_DAO = new AuthorDAOImpl();
    private final static ConsoleHelper consoleHelper = ConsoleHelper.getInstance();

    public static void createAuthor() {
        Author author= new Author();
        String[] firstLastName = enterFirstLastName();
        author.setFirstName(firstLastName[0]);
        author.setLastName(firstLastName[1]);
        try {
            AUTHOR_DAO.create(author);
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }
    public static void updateAuthorByName() {
        String[] firstLastName = enterFirstLastName();
        try {
            Author authorToUpdate = AUTHOR_DAO.getByName(firstLastName[0], firstLastName[1]);
            boolean t = true;
            while (t){
                System.out.println("1. Update firstName"+
                "\n2. Update lastName"+
                "\n3. Add book to author" +
                        "\n4. Delete book" +
                        "\n0. Exit");

                switch (consoleHelper.readInteger()){
                    case 1:{
                        System.out.print("New first name : ");
                        String fN = "";
                        try {
                            fN = consoleHelper.readString();
                        }catch (IOException e){ System.out.println(e.getMessage());}
                        try {
                            authorToUpdate.setFirstName(fN);
                            AUTHOR_DAO.update(authorToUpdate.getId());
                        }catch (RuntimeException e){
                            System.out.println(e.getMessage());
                        }
                        break;
                    }
                    case 2:{
                        System.out.print("New last name: ");
                        String lN = "";
                        try {
                            lN = consoleHelper.readString();
                        }catch (IOException e){ System.out.println(e.getMessage());}
                        try {
                            authorToUpdate.setLastName(lN);
                            AUTHOR_DAO.update(authorToUpdate.getId());
                        }catch (RuntimeException e){
                            System.out.println(e.getMessage());
                        }
                        break;
                    }
                    case 3:{
                        System.out.println("Enter book name (book must already exist): ");
                        String bookName = BookOperationsConsoleRealisation.enterBookName();
                        try {
                            AUTHOR_DAO.addBook(authorToUpdate.getId(), BOOK_DAO.getByName(bookName).getId());
                            BOOK_DAO.addAuthor(BOOK_DAO.getByName(bookName).getId(),authorToUpdate.getId());
                        } catch (RuntimeException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    }
                    case 4:{
                        System.out.println("Enter book name (book must already exist): ");
                        String bookName = BookOperationsConsoleRealisation.enterBookName();
                        try {
                            AUTHOR_DAO.deleteBook(authorToUpdate.getId(), BOOK_DAO.getByName(bookName).getId());
                            BOOK_DAO.deleteAuthor(BOOK_DAO.getByName(bookName).getId(), authorToUpdate.getId());
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

    public static void printAuthorByName(){
        String[] firstLastName = enterFirstLastName();
        try {
            Author authorToUpdate = AUTHOR_DAO.getByName(firstLastName[0], firstLastName[1]);
            System.out.println(AUTHOR_DAO.printAuthor(authorToUpdate));
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    public static void printAuthorsByBookName(){
        String bookName = BookOperationsConsoleRealisation.enterBookName();
        try{
            List<Author> list = AUTHOR_DAO.getByBookName(bookName);
            for (Author a : list)
                AUTHOR_DAO.printAuthor(a);

        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    public static void deleteAuthorFromDB(){
        String[] firstLastName = enterFirstLastName();
        try {
            Author author = AUTHOR_DAO.getByName(firstLastName[0], firstLastName[1]);
            List<String> books = author.getBookList();
            for(int i =0 ; i<books.size(); i++){
                BOOK_DAO.deleteAuthor(books.get(i), author.getId());
            }
            AUTHOR_DAO.delete(author.getId());
        } catch (RuntimeException e) {
            System.out.println("Wrong");
            e.printStackTrace();
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

}
