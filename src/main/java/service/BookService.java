package service;

import model.Book;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class BookService {
    private final HibernateService hibernateService;

    public BookService() {
        this.hibernateService = new HibernateService();
    }

    public Book getBookById(Long id) {
        EntityManager entityManager = hibernateService.getEntityManager();
        try {
            return entityManager.find(Book.class, id);
        } finally {
            entityManager.close();
        }
    }

    public List<Book> getAllBooks() {
        EntityManager entityManager = hibernateService.getEntityManager();
        try {
            return entityManager.createQuery("SELECT b FROM Book b", Book.class).getResultList();
        } finally {
            entityManager.close();
        }
    }

    public void saveBook(Book book) {
        EntityManager entityManager = hibernateService.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(book);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    public void updateBook(Book book) {
        EntityManager entityManager = hibernateService.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(book);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    public boolean deleteBook(Long id) {
        EntityManager entityManager = hibernateService.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Book book = entityManager.find(Book.class, id);
            if (book != null) {
                entityManager.remove(book);
                transaction.commit();
                return true;
            } else {
                transaction.rollback();
                return false;
            }
        } finally {
            entityManager.close();
        }
    }
}
