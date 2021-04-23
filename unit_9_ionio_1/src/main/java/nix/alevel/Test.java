package nix.alevel;

import nix.alevel.dao.AuthorDAO;
import nix.alevel.dao.BookDAO;
import nix.alevel.dao.impl.AuthorDAOImpl;
import nix.alevel.dao.impl.BookDAOImpl;
import nix.alevel.entity.Author;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
    public static void main(String[] args) {
        /*AuthorDAO authorDAO = new AuthorDAOImpl();
        BookDAO bookDAO = new BookDAOImpl();

        Author author = new Author();
        author.setFirstName("AAA");
        author.setLastName("BBB");
        authorDAO.create(author);*/
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy hh:mm:ss");
        Date created = null; 

        try {
            created = dateFormat.parse(dateFormat.format(new Date()));
        } catch (ParseException e) {
                e.printStackTrace();
        }
        System.out.println(created.toString());
        
    }
}
