package service;

import model.Book;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class BookService extends HibernateService {

    public void saveBook(Book book) {
        save(book);
    }

    public Book getBookById(int id) {
        return findById(Book.class, id);
    }

    public void deleteBook(Book book) {
        delete(book);
    }

    public List<Book> getAllBooks() {
        try (Session session = getSession()) {
            return session.createQuery("FROM Book", Book.class).list();
        }
    }
}
