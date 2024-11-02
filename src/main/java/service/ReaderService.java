package service;

import model.Book;
import model.Reader;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

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
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            reader.borrowBook(book);
            session.update(reader);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void returnBook(Reader reader, Book book) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            reader.returnBook(book);
            session.update(reader);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public List<Reader> getAllReaders() {
        try (Session session = getSession()) {
            return session.createQuery("FROM Reader", Reader.class).list();
        }
    }
}
