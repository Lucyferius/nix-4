package nix.alevel.dao;

import nix.alevel.entity.Book;

import java.util.List;

public interface BookDAO extends BaseEntityService<Book> {

    Book getByName(String name);
    List<Book> getByAuthorName(String firstName, String secondName);
    void addAuthor(String idBook, String idAuthor);
    void deleteAuthor(String idBook, String idAuthor);
    String printBook(Book book);
}
