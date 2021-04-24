import nix.alevel.dao.impl.AuthorDAOImpl;
import nix.alevel.dao.impl.BookDAOImpl;
import nix.alevel.dao.AuthorDAO;
import nix.alevel.dao.BookDAO;
import nix.alevel.db.DBInMemory;
import nix.alevel.entity.Author;
import nix.alevel.entity.Book;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.runners.MethodSorters;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MainTest {
    private static final BookDAO BOOK_DAO = new BookDAOImpl();
    private static final AuthorDAO AUTHOR_DAO = new AuthorDAOImpl();
    private static final DBInMemory db = DBInMemory.getInstance();

    @Test
    @Order(1)
    public void t1createAuthor(){
        int size = AUTHOR_DAO.readAll().size();
        boolean check = false;
        Author author = new Author();
        author.setFirstName("TestFirstName");
        author.setLastName("TestLastName");
        try {
            AUTHOR_DAO.create(author);
            check =true;
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
        if(check)
            Assert.assertEquals(size+1,AUTHOR_DAO.readAll().size());
        if(!check)
            Assert.assertEquals(size,AUTHOR_DAO.readAll().size());

    }
    @Test
    @Order(2)
    public void t2createBook(){
        Book book = new Book();
        book.setName("TestBook");
       try {
           BOOK_DAO.create(book);
       }catch (RuntimeException e){
           System.out.println(e.getMessage());
       }
    }
    @Test
    @Order(3)
    public void t3printBooks(){
        try {
            for (Book book: BOOK_DAO.readAll()){
                System.out.println(BOOK_DAO.printBook(book));
            }
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    @Test
    @Order(4)
    public void t4printAuthors(){
        try {
            for (Author author: AUTHOR_DAO.readAll()){
                System.out.println(AUTHOR_DAO.printAuthor(author));
            }
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    @Order(5)
    public void t5updateAuthorNames(){
        Author a = AUTHOR_DAO.getByName("TestFirstName","TestLastName" );
        if(!db.isAuthorExistByName("TestNewName", "TestNewName")) {
            a.setFirstName("TestNewName");
            a.setLastName("TestNewName");
            AUTHOR_DAO.update(a);
        }
        System.out.println("author = " + a.getFirstName() + " "+ a.getLastName());
        Assert.assertNotNull(AUTHOR_DAO.getByName("TestNewName", "TestNewName"));
    }

    @Test
    @Order(7)
    public void t6updateBook(){
        Book book = BOOK_DAO.getByName("TestBook");
        if(!db.isBookExistByName("NewNameOfBook")) {
            book.setName("NewNameOfBook");
            BOOK_DAO.update(book);
        }
        Assert.assertNotNull(BOOK_DAO.getByName(book.getName()));
    }

    @Test
    @Order(8)
    public void t7deleteAuthor(){
        Author author = AUTHOR_DAO.getByName("TestNewName","TestNewName");
        List<String> books = author.getBookList();
        for(int i =0 ; i<books.size(); i++){
            BOOK_DAO.deleteAuthor(books.get(i), author.getId());
        }
        AUTHOR_DAO.delete(author.getId());
    }
    @Test
    @Order(9)
    public void t8deleteBook(){
        Book book = BOOK_DAO.getByName("NewNameOfBook");
        List<String> authors = book.getAuthors();
        for (int i= 0; i<authors.size(); i++){
            AUTHOR_DAO.deleteBook(authors.get(i), book.getId());
        }
        BOOK_DAO.delete(BOOK_DAO.getByName("NewNameOfBook").getId());
    }

}
