package service;

import model.Book;
import model.Librarian;
import model.Reader;
import java.util.ArrayList;
import java.util.List;

public class LibraryService {
    private List<Book> books;
    private List<Reader> readers;
    private List<Librarian> librarians;

    public LibraryService() {
        this.books = new ArrayList<>();
        this.readers = new ArrayList<>();
        this.librarians = new ArrayList<>();
    }

    // CRUD operations for books, readers, and librarians
    public void addBook(Book book) {
        books.add(book);
        System.out.println("Додано книгу: " + book);
    }

    public void updateBook(String isbn, String newTitle, String newAuthor) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                book.setTitle(newTitle);
                book.setAuthor(newAuthor);
                System.out.println("Книгу оновлено: " + book);
                return;
            }
        }
        System.out.println("Книжку не знайдено.");
    }

    public void removeBook(String isbn) {
        books.removeIf(book -> book.getIsbn().equals(isbn));
        System.out.println("Книжку видалено.");
    }

    public void addReader(Reader reader) {
        readers.add(reader);
        System.out.println("Додано читача: " + reader);
    }

    public void updateReader(String name, String newName) {
        for (Reader reader : readers) {
            if (reader.getName().equals(name)) {
                reader.setName(newName);
                System.out.println("Читача оновлено: " + reader);
                return;
            }
        }
        System.out.println("Читача не знайдено.");
    }

    public void removeReader(String name) {
        readers.removeIf(reader -> reader.getName().equals(name));
        System.out.println("Читача видалено.");
    }

    public void addLibrarian(Librarian librarian) {
        librarians.add(librarian);
        System.out.println("Бібліотекара додано: " + librarian);
    }

    public void updateLibrarian(String name, String newName) {
        for (Librarian librarian : librarians) {
            if (librarian.getName().equals(name)) {
                librarian.setName(newName);
                System.out.println("Бібліотекара оновлено: " + librarian);
                return;
            }
        }
        System.out.println("Бібліотекара не знайдено.");
    }

    public void removeLibrarian(String name) {
        librarians.removeIf(librarian -> librarian.getName().equals(name));
        System.out.println("Бібліотекара видалено.");
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<Reader> getReaders() {
        return readers;
    }

    public List<Librarian> getLibrarians() {
        return librarians;
    }

    public void showBooks() {
        if (books.isEmpty()) {
            System.out.println("Немає книжок.");
        } else {
            books.forEach(System.out::println);
        }
    }

    public void showReaders() {
        if (readers.isEmpty()) {
            System.out.println("Немає читачів.");
        } else {
            readers.forEach(System.out::println);
        }
    }

    public void showLibrarians() {
        if (librarians.isEmpty()) {
            System.out.println("Немає бібліотекарів.");
        } else {
            librarians.forEach(System.out::println);
        }
    }
}
