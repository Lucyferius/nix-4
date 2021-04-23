package nix.alevel.dao.impl;

import nix.alevel.dao.AuthorDAO;
import nix.alevel.db.DBInMemory;
import nix.alevel.entity.Author;
import nix.alevel.entity.Book;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class AuthorDAOImpl implements AuthorDAO {
    private static final Logger logger = LogManager.getLogger(AuthorDAOImpl.class);
    private final DBInMemory dbInMemory = DBInMemory.getInstance();

    @Override
    public void create(Author author) {
        logger.info("Check is author exist by name");
        if(dbInMemory.isAuthorExistByName(author.getFirstName(), author.getLastName())) {
            logger.error("Author exist");
            throw new RuntimeException("Author already exist");
        }
        logger.info("Start author creating");
        dbInMemory.createAuthor(author);
        logger.info("Author created");

    }

    @Override
    public List<Author> readAll() {
        logger.info("Start reading all");
        List<Author> list = dbInMemory.findAllExistAuthors();
        if (list.isEmpty())
            logger.info("List is empty");
        logger.info("Returning all authors");
        return list;
    }

    @Override
    public void update(Author author) {
        logger.info("Start of updating author");
        dbInMemory.updateAuthor(author);
        logger.info("Author is updated");
    }

    @Override
    public void delete(String id) {
        logger.info("Start of deleting author");
        dbInMemory.deleteAuthor(id);
        logger.info("Deleting is done");
    }

    @Override
    public Author getById(String id) {
        logger.info("Start searching author by id");
        return findAuthorById(id);
    }
    @Override
    public Author getByName(String firstName, String lastName) {
        logger.info("Start searching author by name");
        if (dbInMemory.isAuthorExistByName(firstName, lastName)) {
            logger.info("Searching by name = " + firstName + " " + lastName +" is done");
            return dbInMemory.findAuthorByName(firstName, lastName);
        }
        logger.error("Author with name " + firstName + " "+lastName + " doesn`t exist");
        throw new RuntimeException("Author with name " + firstName + " "+lastName + " doesn`t exist");
    }

    @Override
    public List<Author> getByBookName(String name) {
        logger.info("Getting book by author name");
        Book book = dbInMemory.findBookByName(name);
        List<Author> list = dbInMemory.getAuthorsByBookId(dbInMemory.findBookByName(name).getId());
        if (list.isEmpty()) {
            logger.error("There is any authors by book name");
            throw new RuntimeException("There is any author by book name " + name);
        }
        logger.info("Return found books");
        return list;
    }

    @Override
    public void addBook(String idAuthor, String idBook) {
        logger.info("Start of adding book to author");
        if(dbInMemory.isBookExist(idBook) && dbInMemory.isAuthorExist(idAuthor)){
            dbInMemory.addBookToAuthor(idAuthor, idBook);
            logger.info("Adding book is completed");
        }else logger.error("Added book or author don`t exist");
    }

    @Override
    public void deleteBook(String idAuthor, String idBook) {
        logger.info("Start deleting book in author");
        if(dbInMemory.isAuthorExist(idAuthor) &&  dbInMemory.findAuthorById(idAuthor).getBookList()
                .stream().anyMatch(bookId -> bookId.equals(idBook))){
            dbInMemory.deleteBookInAuthor(idAuthor, idBook);
            logger.info("Book is deleted in author");
        }
    }

    @Override
    public String printAuthor(Author author) {
        if(!author.getIsExist()) return "";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n\t\t Books:");
        if(author.getBookList().size() == 0) stringBuilder.append(" empty");
        else {
            List<String> books = author.getBookList();
            for (String idBook : books) {
                if(dbInMemory.findBookById(idBook)!=null) {
                    stringBuilder.append("\n\t\t\t\tBook name = ")
                            .append(dbInMemory.findBookById(idBook).getName())
                            .append("; Id = ")
                            .append(idBook);
                }
            }
        }
        return "\nAuthor: "+
                "\n\t\t Id = " + author.getId()+
                ";\n\t\t FirstName = " +author.getFirstName()+
                ";\n\t\t LastName = " + author.getLastName()+
                stringBuilder;
    }

    private Author findAuthorById(String id){
        if (dbInMemory.isAuthorExist(id))
            return dbInMemory.findAuthorById(id);
        else {
            logger.error("Author doesn`t exist");
            throw new RuntimeException("Author doesn`t exist");
        }
    }
}
