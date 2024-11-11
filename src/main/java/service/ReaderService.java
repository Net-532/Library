package service;

import model.Reader;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class ReaderService extends HibernateService {

    public Reader findByName(String name) {
        EntityManager entityManager = getEntityManager();
        try {
            TypedQuery<Reader> query = entityManager.createQuery("SELECT r FROM Reader r WHERE r.name = :name", Reader.class);
            query.setParameter("name", name);
            return query.getSingleResult();
        } finally {
            entityManager.close();
        }
    }

    public List<Reader> findAll() {
        EntityManager entityManager = getEntityManager();
        try {
            TypedQuery<Reader> query = entityManager.createQuery("SELECT r FROM Reader r", Reader.class);
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }

}
