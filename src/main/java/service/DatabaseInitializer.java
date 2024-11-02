package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitializer {
    public static void initializeDatabase() {
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/init.sql"))) {

            StringBuilder sqlBuilder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                sqlBuilder.append(line.trim());

                // Виконати команду, якщо рядок завершується крапкою з комою
                if (line.trim().endsWith(";")) {
                    statement.execute(sqlBuilder.toString());
                    sqlBuilder.setLength(0);  // Очистити builder для наступної команди
                }
            }
            System.out.println("Database initialized successfully!");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Database initialized failed!");

        }
    }
}