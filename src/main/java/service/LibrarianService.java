package service;

import model.Librarian;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
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
        EntityManager entityManager = getEntityManager();
        try {
            TypedQuery<Librarian> query = entityManager.createQuery("FROM Librarian", Librarian.class);
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }
}
