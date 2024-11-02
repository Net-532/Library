package service;

import model.Book;
import model.Librarian;
import model.Reader;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class LibraryService {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibraryPersistence");

    // Add a book
    public void addBook(Book book) {
        EntityTransaction transaction = null;
        EntityManager em = emf.createEntityManager();
        try {
            transaction = em.getTransaction();
            transaction.begin();
            em.persist(book);
            transaction.commit();
            System.out.println("Книга успішно додана до бази даних.");
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Помилка додавання книги. Відкат транзакції.");
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // Update a book
    public void updateBook(String isbn, String newTitle, String newAuthor) {
        EntityTransaction transaction = null;
        EntityManager em = emf.createEntityManager();
        try {
            transaction = em.getTransaction();
            transaction.begin();
            Query query = em.createQuery("FROM Book WHERE isbn = :isbn");
            query.setParameter("isbn", isbn);
            Book book = (Book) query.getSingleResult();

            if (book != null) {
                book.setTitle(newTitle);
                book.setAuthor(newAuthor);
                em.merge(book); // Use merge for updates
                transaction.commit();
                System.out.println("Книга успішно оновлена.");
            } else {
                System.out.println("Книга з таким ISBN не знайдена.");
            }
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // Remove a book
    public void removeBook(String isbn) {
        EntityTransaction transaction = null;
        EntityManager em = emf.createEntityManager();
        try {
            transaction = em.getTransaction();
            transaction.begin();
            Query query = em.createQuery("DELETE FROM Book WHERE isbn = :isbn");
            query.setParameter("isbn", isbn);
            int result = query.executeUpdate();

            if (result > 0) {
                transaction.commit();
                System.out.println("Книга успішно видалена.");
            } else {
                System.out.println("Книга з таким ISBN не знайдена.");
            }
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // Show all books
    public void showBooks() {
        EntityManager em = emf.createEntityManager();
        try {
            List<Book> books = em.createQuery("FROM Book", Book.class).getResultList();
            books.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // Add a reader
    public void addReader(Reader reader) {
        EntityTransaction transaction = null;
        EntityManager em = emf.createEntityManager();
        try {
            transaction = em.getTransaction();
            transaction.begin();
            em.persist(reader);
            transaction.commit();
            System.out.println("Читача успішно додано.");
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // Update a reader
    public void updateReader(int id, String newName) {
        EntityTransaction transaction = null;
        EntityManager em = emf.createEntityManager();
        try {
            transaction = em.getTransaction();
            transaction.begin();
            Reader reader = em.find(Reader.class, id);
            if (reader != null) {
                reader.setName(newName);
                em.merge(reader); // Use merge for updates
                transaction.commit();
                System.out.println("Читача успішно оновлено.");
            } else {
                System.out.println("Читача з таким ID не знайдено.");
            }
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // Remove a reader
    public void removeReader(String name) {
        EntityTransaction transaction = null;
        EntityManager em = emf.createEntityManager();
        try {
            transaction = em.getTransaction();
            transaction.begin();
            Query query = em.createQuery("DELETE FROM Reader WHERE name = :name");
            query.setParameter("name", name);
            int result = query.executeUpdate();

            if (result > 0) {
                transaction.commit();
                System.out.println("Читача успішно видалено.");
            } else {
                System.out.println("Читача з таким ім'ям не знайдено.");
            }
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // Show all readers
    public void showReaders() {
        EntityManager em = emf.createEntityManager();
        try {
            List<Reader> readers = em.createQuery("FROM Reader", Reader.class).getResultList();
            readers.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // Add a librarian
    public void addLibrarian(Librarian librarian) {
        EntityTransaction transaction = null;
        EntityManager em = emf.createEntityManager();
        try {
            transaction = em.getTransaction();
            transaction.begin();
            em.persist(librarian);
            transaction.commit();
            System.out.println("Бібліотекаря успішно додано.");
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // Update a librarian
    public void updateLibrarian(int id, String newName) {
        EntityTransaction transaction = null;
        EntityManager em = emf.createEntityManager();
        try {
            transaction = em.getTransaction();
            transaction.begin();
            Librarian librarian = em.find(Librarian.class, id);
            if (librarian != null) {
                librarian.setName(newName);
                em.merge(librarian); // Use merge for updates
                transaction.commit();
                System.out.println("Бібліотекаря успішно оновлено.");
            } else {
                System.out.println("Бібліотекаря з таким ID не знайдено.");
            }
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // Remove a librarian
    public void removeLibrarian(String name) {
        EntityTransaction transaction = null;
        EntityManager em = emf.createEntityManager();
        try {
            transaction = em.getTransaction();
            transaction.begin();
            Query query = em.createQuery("DELETE FROM Librarian WHERE name = :name");
            query.setParameter("name", name);
            int result = query.executeUpdate();

            if (result > 0) {
                transaction.commit();
                System.out.println("Бібліотекаря успішно видалено.");
            } else {
                System.out.println("Бібліотекаря з таким ім'ям не знайдено.");
            }
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // Show all librarians
    public void showLibrarians() {
        EntityManager em = emf.createEntityManager();
        try {
            List<Librarian> librarians = em.createQuery("FROM Librarian", Librarian.class).getResultList();
            librarians.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
