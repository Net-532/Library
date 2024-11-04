package service;

import model.Book;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

@Service
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
        EntityManager entityManager = getEntityManager();
        try {
            return entityManager.createQuery("FROM Book", Book.class).getResultList();
        } finally {
            entityManager.close();
        }
    }
}
