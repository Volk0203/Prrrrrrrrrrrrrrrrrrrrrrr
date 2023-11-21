package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    private static final String DB_URL = "spring.datasource.url=jdbc:h2:file:./testdb";
    private static final String DB_USERNAME = "username";
    private static final String DB_PASSWORD = "password";
    public static void deleteMessage(int userId, int messageId) {
        String deleteQuery = "DELETE FROM messages WHERE user_id = ? AND message_id = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, messageId);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                System.out.println("No message found with user id " + userId + " and message id " + messageId);
            } else {
                System.out.println("Message with user id " + userId + " and message id " + messageId + " deleted successfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        deleteMessage(1, 5);
    }
}
