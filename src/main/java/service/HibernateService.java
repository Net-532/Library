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
        Transaction(em -> em.persist(entity));
    }

    public <T> T findById(Class<T> clazz, int id) {
        EntityManager entityManager = getEntityManager();
        try {
            return entityManager.find(clazz, id);
        } finally {
            entityManager.close();
        }
    }

    public <T> void update(T entity) {
        Transaction(entityManager -> entityManager.merge(entity));
    }

    public <T> void delete(T entity) {
        Transaction(entityManager -> entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity)));
    }

    private void Transaction(EntityManagerOperation operation) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            operation.accept(entityManager);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    @FunctionalInterface
    protected interface EntityManagerOperation {
        void accept(EntityManager entityManager);
    }

    public void closeEntityManagerFactory() {
        entityManagerFactory.close();
    }
}
