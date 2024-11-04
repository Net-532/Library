package service;

import model.Book;
import model.Reader;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

@Service
public class ReaderService extends HibernateService {

    public void saveReader(Reader reader) {
        save(reader);
    }

    public Reader getReaderById(int id) {
        return findById(Reader.class, id);
    }

    public void deleteReader(Reader reader) {
        delete(reader);
    }

    public void borrowBook(Reader reader, Book book) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            reader.borrowBook(book);
            entityManager.merge(reader);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public void returnBook(Reader reader, Book book) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            reader.returnBook(book);
            entityManager.merge(reader);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public List<Reader> getAllReaders() {
        EntityManager entityManager = getEntityManager();
        try {
            return entityManager.createQuery("FROM Reader", Reader.class).getResultList();
        } finally {
            entityManager.close();
        }
    }
}
