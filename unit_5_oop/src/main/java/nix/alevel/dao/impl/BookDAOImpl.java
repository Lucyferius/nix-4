package nix.alevel.dao.impl;

import nix.alevel.dao.BookDAO;
import nix.alevel.db.DBInMemory;
import nix.alevel.entity.Book;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class BookDAOImpl implements BookDAO {
    private static final Logger logger = LogManager.getLogger(AuthorDAOImpl.class);
    private final DBInMemory dbInMemory = DBInMemory.getInstance();

    @Override
    public void create(Book book) {
        logger.info("Check is book exist by name");
        if(dbInMemory.isBookExistByName(book.getName())){
            logger.error("Book exist");
            throw new RuntimeException("Book already exist");
        }
        logger.info("Start book creating");
        dbInMemory.createBook(book);
        logger.info("Book created");

    }

    @Override
    public List<Book> readAll() {
        logger.info("Start reading all");
        List<Book> list = dbInMemory.findAllBooks();
        if (list.isEmpty())
            logger.info("List is empty");
        logger.info("Returning all books");
        return list;
    }

    @Override
    public void update(String id) {
        logger.info("Start of updating book");
        dbInMemory.updateBook(findBookById(id));
        logger.info("Book is updated");
    }

    @Override
    public void delete(String id) {
        logger.info("Start of deleting book");
        dbInMemory.deleteBook(findBookById(id).getId());
        logger.info("Deleting is done");
    }

    @Override
    public Book getById(String id) {
        logger.info("Start searching book by id");
        return findBookById(id);
    }

    @Override
    public Book getByName(String name) {
        logger.info("Start searching book by name");
        if (!dbInMemory.isBookExistByName(name)) {
            logger.error("Book with name " + name + " doesn`t exist");
            throw new RuntimeException("Book with name " + name + " doesn`t exist");
        }
        logger.info("Searching by name = " + name +" is done");
        return dbInMemory.findBookByName(name);
    }

    @Override
    public List<Book> getByAuthorName(String firstName, String secondName) {
        logger.info("Getting book by author name");
        List<Book> list =dbInMemory.getBooksByAuthorId(dbInMemory.findAuthorByName(firstName, secondName).getId());
        if (list.isEmpty()) {
            logger.error("There is any book by author name");
            throw new RuntimeException("There is any book by author name " + firstName + " " + secondName);
        }
        logger.info("Return found books");
        return list;
    }

    @Override
    public void addAuthor(String idBook, String idAuthor) {
        logger.info("Start of adding author");
        if(dbInMemory.isBookExist(idBook) && dbInMemory.isAuthorExist(idAuthor)){
            dbInMemory.addAuthorToBook(idBook, idAuthor);
            logger.info("Adding author is completed");
        }else logger.error("Added book or author don`t exist");
    }

    @Override
    public void deleteAuthor(String idBook, String idAuthor) {
        logger.info("Start author deleting in book");
        if(dbInMemory.isBookExist(idBook) &&
                dbInMemory.findBookById(idBook).getAuthors()
                        .stream().anyMatch(authorId -> authorId.equals(idAuthor))){
            dbInMemory.deleteAuthorInBook(idBook, idAuthor);
            logger.info("Author is deleted in book");
        }

    }

    @Override
    public String printBook(Book book) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n\t\t Authors:");
        if(book.getAuthors().size() == 0) stringBuilder.append(" empty");
        else {
            for (String idAuthor : book.getAuthors()) {
                if(idAuthor != null) {
                    stringBuilder.append("\n\t\t\t\t FirstName = ")
                            .append(dbInMemory.findAuthorById(idAuthor).getFirstName())
                            .append(";  LastName = ")
                            .append(dbInMemory.findAuthorById(idAuthor).getLastName())
                            .append("; Id = ")
                            .append(dbInMemory.findAuthorById(idAuthor).getId());
                }
            }
        }
        return "\nBook: "+
                "\n\t\t Id = " + book.getId()+
                "\n\t\t Book name = " + book.getName() +
                stringBuilder;
    }

    private Book findBookById(String id){
        if (dbInMemory.isBookExist(id))
            return dbInMemory.findBookById(id);
        else {
            logger.error("Book doesn`t exist");
            throw new RuntimeException("Book doesn`t exist");
        }
    }
}
