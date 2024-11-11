package service;

import model.Librarian;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class LibrarianService extends HibernateService {

    public Librarian findByName(String name) {
        EntityManager entityManager = getEntityManager();
        try {
            TypedQuery<Librarian> query = entityManager.createQuery("SELECT l FROM Librarian l WHERE l.name = :name", Librarian.class);
            query.setParameter("name", name);
            return query.getSingleResult();
        } finally {
            entityManager.close();
        }
    }

    public List<Librarian> findAll() {
        EntityManager entityManager = getEntityManager();
        try {
            TypedQuery<Librarian> query = entityManager.createQuery("SELECT l FROM Librarian l", Librarian.class);
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }
}
