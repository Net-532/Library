package service;

import model.Book;
import model.Librarian;
import model.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryService {

    private final BookService bookService;
    private final ReaderService readerService;
    private final LibrarianService librarianService;

    @Autowired
    public LibraryService(BookService bookService, ReaderService readerService, LibrarianService librarianService) {
        this.bookService = bookService;
        this.readerService = readerService;
        this.librarianService = librarianService;
    }

    // Methods for books
    public void addBook(Book book) {
        bookService.save(book);
        System.out.println("Книга успішно додана до бази даних.");
    }

    public void updateBook(Book book) {
        bookService.update(book);
        System.out.println("Книга успішно оновлена.");
    }

    public void removeBook(String isbn) {
        Book book = bookService.findByIsbn(isbn);
        if (book != null) {
            bookService.delete(book);
            System.out.println("Книга успішно видалена.");
        } else {
            System.out.println("Книга з таким ISBN не знайдена.");
        }
    }

    public List<Book> showBooks() {
        return bookService.findAll();
    }

    // Methods for readers
    public void addReader(Reader reader) {
        readerService.save(reader);
        System.out.println("Читача успішно додано.");
    }

    public void updateReader(Reader reader) {
        readerService.update(reader);
        System.out.println("Читача успішно оновлено.");
    }

    public void removeReader(String name) {
        Reader reader = readerService.findByName(name);
        if (reader != null) {
            readerService.delete(reader);
            System.out.println("Читача успішно видалено.");
        } else {
            System.out.println("Читача з таким ім'ям не знайдено.");
        }
    }

    public List<Reader> showReaders() {
        return readerService.findAll();
    }

    // Methods for librarians
    public void addLibrarian(Librarian librarian) {
        librarianService.save(librarian);
        System.out.println("Бібліотекаря успішно додано.");
    }

    public void updateLibrarian(Librarian librarian) {
        librarianService.update(librarian);
        System.out.println("Бібліотекаря успішно оновлено.");
    }

    public void removeLibrarian(String name) {
        Librarian librarian = librarianService.findByName(name);
        if (librarian != null) {
            librarianService.delete(librarian);
            System.out.println("Бібліотекаря успішно видалено.");
        } else {
            System.out.println("Бібліотекаря з таким ім'ям не знайдено.");
        }
    }

    public List<Librarian> showLibrarians() {
        return librarianService.findAll();
    }
}
