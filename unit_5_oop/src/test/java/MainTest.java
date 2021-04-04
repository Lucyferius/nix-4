import nix.alevel.dao.impl.AuthorDAOImpl;
import nix.alevel.dao.impl.BookDAOImpl;
import nix.alevel.dao.AuthorDAO;
import nix.alevel.dao.BookDAO;
import nix.alevel.entity.Author;
import nix.alevel.entity.Book;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.runners.MethodSorters;

import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MainTest {
    private static final BookDAO BOOK_DAO = new BookDAOImpl();
    private static final AuthorDAO AUTHOR_DAO = new AuthorDAOImpl();

    @BeforeAll
    public static void init(){
        for(int i=0; i<10; i++){
            Author author = new Author();
            author.setFirstName("Ivan" + i);
            author.setLastName("Spider" + i);
            AUTHOR_DAO.create(author);
        }
        for (int i =0; i<10; i++){
            Book book = new Book();
            book.setName("Book"+i);
            BOOK_DAO.create(book);
        }
    }

    @Test
    @Order(1)
    public void t1createAuthor(){
        int size = AUTHOR_DAO.readAll().size();
        Author author = new Author();
        author.setFirstName("TestFirstName");
        author.setLastName("TestLastName");
        AUTHOR_DAO.create(author);
        Assert.assertEquals(size+1,AUTHOR_DAO.readAll().size());

    }
    @Test
    @Order(2)
    public void t2createBook(){
        int size = BOOK_DAO.readAll().size();
        Book book = new Book();
        book.setName("TestBook");
        BOOK_DAO.create(book);
        Assert.assertEquals(size+1,AUTHOR_DAO.readAll().size());
    }
    @Test
    @Order(3)
    public void t3printBooks(){
        for (Book b: BOOK_DAO.readAll()){
            System.out.println(BOOK_DAO.printBook(b));
        }
    }
    @Test
    @Order(4)
    public void t4printAuthors(){
        for (Author a: AUTHOR_DAO.readAll()){
            System.out.println(AUTHOR_DAO.printAuthor(a));
        }
    }

    @Test
    @Order(5)
    public void t5updateAuthorNames(){
        Author a = AUTHOR_DAO.getByName("TestFirstName","TestLastName" );
        a.setFirstName("TestNewName");
        a.setLastName("TestNewName");
        AUTHOR_DAO.update(a.getId());
        Assert.assertEquals(a, AUTHOR_DAO.getByName("TestNewName","TestNewName" ));
    }
    @Test
    @Order(6)
    public void t6updateAuthorAll(){
        Author a = AUTHOR_DAO.getByName("TestNewName","TestNewName" );
        a.setFirstName("TestNew2");
        a.setLastName("TestNew2");
        AUTHOR_DAO.addBook(a.getId(),BOOK_DAO.getByName("TestBook").getId());
        BOOK_DAO.addAuthor(BOOK_DAO.getByName("TestBook").getId(), a.getId());

    }
    @Test
    @Order(7)
    public void t7updateBook(){
        Book book = BOOK_DAO.getByName("TestBook");
        book.setName("NewNameOfBook");
        BOOK_DAO.update(book.getId());
        Assert.assertEquals(book, BOOK_DAO.getByName("NewNameOfBook"));
    }
    @Test
    @Order(8)
    public void t8deleteAuthor(){
        Author author = AUTHOR_DAO.getByName("TestNew2","TestNew2");
        List<String> books = author.getBookList();
        for(int i =0 ; i<books.size(); i++){
            BOOK_DAO.deleteAuthor(books.get(i), author.getId());
        }
        AUTHOR_DAO.delete(author.getId());
    }
    @Test
    @Order(9)
    public void t9deleteBook(){
        Book book = BOOK_DAO.getByName("NewNameOfBook");
        List<String> authors = book.getAuthors();
        for (int i= 0; i<authors.size(); i++){
            AUTHOR_DAO.deleteBook(authors.get(i), book.getId());
        }
        BOOK_DAO.delete(BOOK_DAO.getByName("NewNameOfBook").getId());
    }

}
