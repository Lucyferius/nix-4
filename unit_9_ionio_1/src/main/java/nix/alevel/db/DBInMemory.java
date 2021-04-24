package nix.alevel.db;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import nix.alevel.entity.Author;
import nix.alevel.entity.BaseEntity;
import nix.alevel.entity.Book;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class DBInMemory {
    private static DBInMemory db;

    private final String authorFile = "authors.csv";
    private final String bookFile = "books.csv";
    private final DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy hh:mm:ss", Locale.ENGLISH);
    private final String[] headerAuthor = {"id", "date", "firstName", "secondName", "books", "isExist"};
    private final String[] headerBook = new String[]{"id", "date", "name",  "authors", "isExist"};

    private void initHeader(String header[], String file){
        List<String[]> csvData = new ArrayList<>();
        try (CSVWriter writer = new CSVWriter(new FileWriter(file))) {
            csvData.add(header);
            writer.writeAll(csvData);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private DBInMemory() {
        if(!(new File(authorFile).exists())){
            initHeader(headerAuthor, authorFile);
            initHeader(headerBook, bookFile);
        }
    }

    public static DBInMemory getInstance() {
        if (db == null) {
            db = new DBInMemory();
        }
        return db;
    }
    public void createAuthor(Author author) {
        author.setId(generateId(UUID.randomUUID().toString(), Author.class));
        author.setIsExist(true);

        String[] fields = parseAuthorToArray(author);
        try (CSVWriter writer = new CSVWriter(new FileWriter(authorFile, true))) {
            writer.writeNext(fields);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createBook(Book book) {
        book.setId(generateId(UUID.randomUUID().toString(), Book.class));
        book.setIsExist(true);
        String[] fields = parseBookToArray(book);
        try (CSVWriter writer = new CSVWriter(new FileWriter(bookFile, true))) {
            writer.writeNext(fields);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateAuthor(Author author){
        List<Author> authorsCSV = findAllAuthors();
        for (int i = 0; i<authorsCSV.size();i++){
            if(authorsCSV.get(i).getIsExist()){
                if(authorsCSV.get(i).getId().equals(author.getId())){
                    authorsCSV.get(i).setLastName(author.getLastName());
                    authorsCSV.get(i).setFirstName(author.getFirstName());
                    authorsCSV.get(i).setBookList(author.getBookList());
                }
            }
        }
        recreateAuthors(authorsCSV);
    }
    public void recreateAuthors(List<Author> authorsCSV){
        initHeader(headerAuthor, authorFile);
        for (Author author : authorsCSV) {
            String[] fields = parseAuthorToArray(author);
            for (String s : author.getBookList())
                if (fields[4].equals(" ")) {
                    fields[4] = fields[3].trim();
                    fields[4] = s + " ";
                } else fields[4] = fields[4] +  s + " ";
            try (CSVWriter writer = new CSVWriter(new FileWriter(authorFile, true))) {
                writer.writeNext(fields);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateBook(Book book){
        List<Book> bookCSV = findAllBooks();
        for (int i = 0; i<bookCSV.size();i++){
            if(bookCSV.get(i).getIsExist()){
                if(bookCSV.get(i).getId().equals(book.getId())){
                    bookCSV.get(i).setName(book.getName());
                    bookCSV.get(i).setAuthors(book.getAuthors());
                }
            }
        }
        recreateBooks(bookCSV);
    }
    public List<Author> findAllExistAuthors() {
        List<Author> authors = findAllAuthors();
        return authors.stream().filter(BaseEntity::getIsExist).collect(Collectors.toList());
    }
    public List<Book> findAllExistBooks() {
        List<Book> books = findAllBooks();
        return books.stream().filter(BaseEntity::getIsExist).collect(Collectors.toList());
    }
    private List<Author> findAllAuthors() {
        List<Author> res = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(authorFile))) {
            List<String[]> csvData = reader.readAll();
            for (int i = 1; i < csvData.size(); i++) {
                Author author = new Author();
                author.setId(csvData.get(i)[0]);
                Date date = null;
                try { date = dateFormat.parse(csvData.get(i)[1]);
                } catch (ParseException e) {
                    System.out.println("Unsupported format");
                }
                author.setCreated(date);
                author.setFirstName(csvData.get(i)[2]);
                author.setLastName(csvData.get(i)[3]);
                author.setBookList(new ArrayList<>(Arrays.asList(csvData.get(i)[4].split("[ ]"))));
                author.setIsExist(Boolean.parseBoolean(csvData.get(i)[5]));
                res.add(author);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return res;
    }
    private List<Book> findAllBooks() {
        List<Book> res = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(bookFile))) {
            List<String[]> csvData = reader.readAll();
            for (int i = 1; i < csvData.size(); i++) {
                Book book = new Book();
                book.setId(csvData.get(i)[0]);
                book.setName(csvData.get(i)[2]);
                Date date = null;
                try { date = dateFormat.parse(csvData.get(i)[1]);
                } catch (ParseException e) {
                    System.out.println("Unsupported format");
                }
                book.setCreated(date);
                book.setAuthors(new ArrayList<>(Arrays.asList(csvData.get(i)[3].split("[ ]"))));
                book.setIsExist(Boolean.parseBoolean(csvData.get(i)[4]));
                res.add(book);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return res;
    }
    public void recreateBooks(List<Book> booksCSV){
        initHeader(headerBook, bookFile);
        for (Book book : booksCSV) {
            String[] fields = parseBookToArray(book);
            for (String s : book.getAuthors())
                if (fields[3].equals(" ")) {
                    fields[3] = fields[3].trim();
                    fields[3] = s + " ";
                } else  fields[3] += s + " ";
            try (CSVWriter writer = new CSVWriter(new FileWriter(bookFile, true))) {
                writer.writeNext(fields);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteAuthor(String id) {
        List<Author> authors = findAllAuthors();
        List<Book> books = null;
        for (Author author : authors){
            if(author.getIsExist()){
                if(author.getId().equals(id)){
                    author.setIsExist(false);
                    books = findAllBooks();
                    for (Book book: books){
                        List<String> authorsOfBook = book.getAuthors();
                        if(authorsOfBook.equals(author.getId())){
                            book.getAuthors().remove(author.getId());
                            updateBook(book);
                        }
                    }
                }

            }
        }
        recreateAuthors(authors);
        recreateBooks(books);
    }

    public void deleteBook(String id) {
        List<Book> books = findAllBooks();
        List<Author> authors = null;
        for (Book book : books){
            if(book.getIsExist()){
                if(book.getId().equals(id)){
                    book.setIsExist(false);
                    authors = findAllAuthors();
                    for (Author author: authors){
                        List<String> booksOfAuthor = author.getBookList();
                        if(booksOfAuthor.equals(book.getId())){
                            author.getBookList().remove(book.getId());
                            updateAuthor(author);
                        }
                    }
                }
            }
        }

        recreateAuthors(authors);
        recreateBooks(books);
    }

    private String[] parseAuthorToArray(Author a){
        String[] res = new String[6];
        res[0] = a.getId();
        res[1] = dateFormat.format(a.getCreated());
        res[2] = a.getFirstName();
        res[3] = a.getLastName();
        res[4] = " ";
        res[5] = String.valueOf(a.getIsExist());
        return res;
    }

    private String[] parseBookToArray(Book b){
        String[] res = new String[5];
        res[0] = b.getId();
        res[1] = dateFormat.format(b.getCreated());
        res[2] = b.getName();
        res[3] = " ";
        res[4] = String.valueOf(b.getIsExist());
        return res;
    }

    public Author findAuthorByName(String firstName, String secondName){
        return findAllAuthors().stream().filter(au -> au.getFirstName().equals(firstName)).filter(au -> au.getLastName().equals(secondName)).filter(BaseEntity::getIsExist)
                .findFirst().orElse(null);
    }


    public Book findBookByName(String name){
        List<Book> books = findAllBooks();
        return books.stream().filter(b -> b.getName().equals(name)).filter(BaseEntity::getIsExist).findFirst().orElse(null);
    }

    public boolean isAuthorExist(String id) {
        List<Author> authors =findAllAuthors();
        return authors.stream().anyMatch(au -> au.getId().equals(id));
    }

    public boolean isBookExist(String id) {
        List<Book> books = findAllBooks();
        return books.stream().anyMatch(b -> b.getId().equals(id));
    }

    public boolean isAuthorExistByName(String firstName, String lastName) {
        List<Author> authors =findAllAuthors();
        return authors.stream().anyMatch(au -> au.getFirstName().equals(firstName) && au.getLastName().equals(lastName) && au.getIsExist());
    }

    public boolean isBookExistByName(String name) {
        List<Book> books = findAllBooks();
        return books.stream().anyMatch(b -> b.getName().equals(name) && b.getIsExist());
    }

    public Author findAuthorById(String id) {
        return findAllAuthors().stream().filter(author ->author.getId().equals(id) && author.getIsExist()).findFirst().orElse(null);
    }

    public Book findBookById(String id) {
        return findAllBooks().stream().filter(book ->book.getId().equals(id)&& book.getIsExist()).findFirst().orElse(null);
    }

    public List<Book> getBooksByAuthorId(String id) {
        List<Book> books = findAllBooks();
        return books.stream().filter(b -> b.getAuthors().contains(id) && b.getIsExist()).collect(Collectors.toList());
    }

    public void addAuthorToBook(String idBook, String idAuthor) {
        Book book = findBookById(idBook);
        if(!book.getAuthors().contains(idAuthor))
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
        return findAllAuthors().stream().filter(author -> author.getBookList().contains(id) && author.getIsExist()).collect(Collectors.toList());
    }

    private <C extends BaseEntity> String generateId(String id, Class<C> aClass) {
        if (aClass.isAssignableFrom(Author.class)) {
            if (findAllAuthors().stream().anyMatch(author -> author.getId().equals(id))) {
                return generateId(UUID.randomUUID().toString(), Author.class);
            }
            return id;
        }
        if (aClass.isAssignableFrom(Book.class)) {
            if (findAllBooks().stream().anyMatch(book -> book.getId().equals(id))) {
                return generateId(UUID.randomUUID().toString(), Book.class);
            }
            return id;
        }
        return null;
    }

}