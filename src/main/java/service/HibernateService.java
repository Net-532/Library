package service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateService {

    private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    protected static Session getSession() {
        return sessionFactory.openSession();
    }

    public <T> void save(T entity) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public <T> T findById(Class<T> clazz, int id) {
        try (Session session = getSession()) {
            return session.get(clazz, id);
        }
    }

    public <T> void delete(T entity) {
        Transaction transaction = null;
        try (Session session = getSession()) {
            transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void closeSessionFactory() {
        sessionFactory.close();
    }
}
