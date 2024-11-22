package service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class HibernateService {

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("LibraryPersistence");

    protected static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    public <T> void save(T entity) {
        EntityTransaction transaction = null;
        EntityManager entityManager = getEntityManager();
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public <T> T findById(Class<T> clazz, int id) {
        EntityManager entityManager = getEntityManager();
        try {
            return entityManager.find(clazz, id);
        } finally {
            entityManager.close();
        }
    }

    public <T> void delete(T entity) {
        EntityTransaction transaction = null;
        EntityManager entityManager = getEntityManager();
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

}