package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/library_db"; // URL до вашої бази даних
    private static final String USER = "root"; // Ім'я користувача
    private static final String PASSWORD = "1212"; // Пароль до бази даних

    public static Connection getConnection() {
        Connection connection = null;
        try {

            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database connected successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to connect to the database.");
        }
        return connection;
    }
}