package service;

import model.Book;
import model.Librarian;
import model.Reader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibraryService {

    // Add a book
    public void addBook(Book book) {
        String sql = "INSERT INTO books (title, author, isbn) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection()) {
            // Початок транзакції
            connection.setAutoCommit(false);

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, book.getTitle());
                statement.setString(2, book.getAuthor());
                statement.setString(3, book.getIsbn());

                statement.executeUpdate();

                // Завершення транзакції
                connection.commit();
                System.out.println("Книга успішно додана до бази даних.");
            } catch (SQLException e) {
                // Відкат транзакції у разі помилки
                connection.rollback();
                System.out.println("Помилка додавання книги. Відкат транзакції.");
                e.printStackTrace();
            } finally {
                // Повернення авто-коміту в попередній стан
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update a book
    public void updateBook(String isbn, String newTitle, String newAuthor) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE books SET title = ?, author = ? WHERE isbn = ?")) {

            statement.setString(1, newTitle);
            statement.setString(2, newAuthor);
            statement.setString(3, isbn);
            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Книга успішно оновлена.");
            } else {
                System.out.println("Книга з таким ISBN не знайдена.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Remove a book
    public void removeBook(String isbn) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM books WHERE isbn = ?")) {

            statement.setString(1, isbn);
            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Книга успішно видалена.");
            } else {
                System.out.println("Книга з таким ISBN не знайдена.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Show all books
    public void showBooks() {
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM books")) {

            List<Book> books = new ArrayList<>();
            while (resultSet.next()) {
                books.add(new Book(
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getString("isbn")
                ));
            }

            books.forEach(System.out::println);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Add a reader
    public void addReader(Reader reader) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            connection.setAutoCommit(false);  // Почати транзакцію

            // Спершу додати до таблиці persons
            try (PreparedStatement personStmt = connection.prepareStatement(
                    "INSERT INTO persons (name) VALUES (?)", Statement.RETURN_GENERATED_KEYS)) {
                personStmt.setString(1, reader.getName());
                personStmt.executeUpdate();

                // Отримати згенерований person_id
                ResultSet generatedKeys = personStmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int personId = generatedKeys.getInt(1);
                    reader.setId(personId);  // Встановити ID для читача

                    // Додати до таблиці readers
                    try (PreparedStatement readerStmt = connection.prepareStatement(
                            "INSERT INTO readers (person_id) VALUES (?)")) {
                        readerStmt.setInt(1, personId);
                        readerStmt.executeUpdate();
                    }
                }
                connection.commit();  // Підтвердити транзакцію
                System.out.println("Читача успішно додано.");
            } catch (SQLException e) {
                connection.rollback();  // Відкат транзакції у разі помилки
                throw e;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update a reader
    public void updateReader(String name, String newName) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE readers SET name = ? WHERE name = ?")) {

            statement.setString(1, newName);
            statement.setString(2, name);
            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Читача успішно оновлено.");
            } else {
                System.out.println("Читача з таким ім'ям не знайдено.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Remove a reader
    public void removeReader(String name) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM readers WHERE name = ?")) {

            statement.setString(1, name);
            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Читача успішно видалено.");
            } else {
                System.out.println("Читача з таким ім'ям не знайдено.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Show all readers
    public void showReaders() {
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM readers")) {

            List<Reader> readers = new ArrayList<>();
            while (resultSet.next()) {
                Reader reader = new Reader(resultSet.getString("name"));
                reader.setId(resultSet.getInt("person_id"));
                readers.add(reader);
            }

            readers.forEach(System.out::println);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Add a librarian
    public void addLibrarian(Librarian librarian) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            connection.setAutoCommit(false);  // Почати транзакцію

            // Спершу додати до таблиці persons
            try (PreparedStatement personStmt = connection.prepareStatement(
                    "INSERT INTO persons (name) VALUES (?)", Statement.RETURN_GENERATED_KEYS)) {
                personStmt.setString(1, librarian.getName());
                personStmt.executeUpdate();

                // Отримати згенерований person_id
                ResultSet generatedKeys = personStmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int personId = generatedKeys.getInt(1);
                    librarian.setId(personId);  // Встановити ID для бібліотекаря

                    // Додати до таблиці librarians
                    try (PreparedStatement librarianStmt = connection.prepareStatement(
                            "INSERT INTO librarians (person_id) VALUES (?)")) {
                        librarianStmt.setInt(1, personId);
                        librarianStmt.executeUpdate();
                    }
                }
                connection.commit();  // Підтвердити транзакцію
                System.out.println("Бібліотекаря успішно додано.");
            } catch (SQLException e) {
                connection.rollback();  // Відкат транзакції у разі помилки
                throw e;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update a librarian
    public void updateLibrarian(String name, String newName) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE librarians SET name = ? WHERE name = ?")) {

            statement.setString(1, newName);
            statement.setString(2, name);
            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Бібліотекаря успішно оновлено.");
            } else {
                System.out.println("Бібліотекаря з таким ім'ям не знайдено.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Remove a librarian
    public void removeLibrarian(String name) {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM librarians WHERE name = ?")) {

            statement.setString(1, name);
            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Бібліотекаря успішно видалено.");
            } else {
                System.out.println("Бібліотекаря з таким ім'ям не знайдено.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Show all librarians
    public void showLibrarians() {
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM librarians")) {

            List<Librarian> librarians = new ArrayList<>();
            while (resultSet.next()) {
                Librarian librarian = new Librarian(resultSet.getString("name"));
                librarian.setId(resultSet.getInt("person_id"));
                librarians.add(librarian);
            }

            librarians.forEach(System.out::println);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
