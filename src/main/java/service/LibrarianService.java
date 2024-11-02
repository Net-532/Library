package service;

import model.Librarian;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class LibrarianService extends HibernateService {

    public void saveLibrarian(Librarian librarian) {
        save(librarian);
    }

    public Librarian getLibrarianById(int id) {
        return findById(Librarian.class, id);
    }

    public void deleteLibrarian(Librarian librarian) {
        delete(librarian);
    }

    public List<Librarian> getAllLibrarians() {
        try (Session session = getSession()) {
            Query<Librarian> query = session.createQuery("FROM Librarian", Librarian.class);
            return query.getResultList();
        }
    }
}
