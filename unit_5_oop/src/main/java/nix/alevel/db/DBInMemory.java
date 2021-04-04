package nix.alevel.db;

import nix.alevel.entity.Author;
import nix.alevel.entity.BaseEntity;
import nix.alevel.entity.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class DBInMemory {
    private static DBInMemory db;

    private final List<Book> booksInDB;
    private final List<Author> authorsInDB;

    private DBInMemory() {
        this.authorsInDB = new ArrayList<>();
        this.booksInDB = new ArrayList<>();
    }

    public static DBInMemory getInstance() {
        if (db == null) {
            db = new DBInMemory();
        }
        return db;
    }
    public void createAuthor(Author author) {
        author.setId(generateId(UUID.randomUUID().toString(), Author.class));
        authorsInDB.add(author);
    }


    public void createBook(Book book) {
        book.setId(generateId(UUID.randomUUID().toString(), Book.class));
        booksInDB.add(book);
    }


    public void updateAuthor(Author author){
        Author current = findAuthorById(author.getId());
        current.setFirstName(author.getFirstName());
        current.setLastName(author.getLastName());
    }

    public void updateBook(Book book){
        Book current = findBookById(book.getId());
        current.setName(book.getName());
        current.setAuthors(book.getAuthors());
    }


    public void deleteAuthor(String id) {
        Author author = findAuthorById(id);
        authorsInDB.remove(author);
        //authorsInDB.removeIf(au -> au.getId().equals(author.getId()));
    }

    public void deleteBook(String id) {
        Book book = findBookById(id);
        booksInDB.remove(book);
        //booksInDB.removeIf(b -> b.getId().equals(book.getId()));
    }


    public Author findAuthorByName(String firstName, String secondName){
        return authorsInDB.stream().filter(au -> au.getFirstName().equals(firstName)).filter(au -> au.getLastName().equals(secondName))
                .findFirst().orElse(null);
    }


    public Book findBookByName(String name){
        return booksInDB.stream().filter(b -> b.getName().equals(name)).findFirst().orElse(null);
    }


    public List<Author> findAllAuthors(){
        return authorsInDB;
    }


    public List<Book> findAllBooks(){
        return booksInDB;
    }


    public boolean isAuthorExist(String id) {
        return authorsInDB.stream().anyMatch(au -> au.getId().equals(id));
    }

    public boolean isBookExist(String id) {
        return booksInDB.stream().anyMatch(b -> b.getId().equals(id));
    }

    public boolean isAuthorExistByName(String firstName, String lastName) {
        return authorsInDB.stream().anyMatch(au -> au.getFirstName().equals(firstName) && au.getLastName().equals(lastName));
    }

    public boolean isBookExistByName(String name) {
        return booksInDB.stream().anyMatch(b -> b.getName().equals(name));
    }

    public Author findAuthorById(String id) {
        Author authorReturn = new Author();
        for (Author author: authorsInDB){
            if(author.getId().equals(id))
                authorReturn = author;
        }
        return authorReturn;
    }

    public Book findBookById(String id) {
        return booksInDB.stream().filter(book ->book.getId().equals(id)).findFirst().orElse(null);
    }

    public List<Book> getBooksByAuthorId(String id) {

        return booksInDB.stream().filter(b -> b.getAuthors().contains(id)).collect(Collectors.toList());
    }

    public void addAuthorToBook(String idBook, String idAuthor) {
        Book book = findBookById(idBook);
        book.getAuthors().add(idAuthor);
        updateBook(book);
    }

    public void addBookToAuthor(String idAuthor, String idBook){
        Author author = findAuthorById(idAuthor);
        author.getBookList().add(idBook);
        updateAuthor(author);
    }

    public void deleteAuthorInBook(String idBook, String idAuthor) {
        Book book = findBookById(idBook);
        book.getAuthors().remove(idAuthor);
        updateBook(book);
    }

    public void deleteBookInAuthor(String idAuthor, String idBook) {
        Author author = findAuthorById(idAuthor);
        author.getBookList().remove(idBook);
        updateAuthor(author);
    }

    public List<Author> getAuthorsByBookId(String id){
        return authorsInDB.stream().filter(author -> author.getBookList().contains(id)).collect(Collectors.toList());
    }

    private <C extends BaseEntity> String generateId(String id, Class<C> aClass) {
        if (aClass.isAssignableFrom(Author.class)) {
            if (authorsInDB.stream().anyMatch(author -> author.getId().equals(id))) {
                return generateId(UUID.randomUUID().toString(), Author.class);
            }
            return id;
        }
        if (aClass.isAssignableFrom(Book.class)) {
            if (booksInDB.stream().anyMatch(book -> book.getId().equals(id))) {
                return generateId(UUID.randomUUID().toString(), Book.class);
            }
            return id;
        }
        return null;
    }

}
