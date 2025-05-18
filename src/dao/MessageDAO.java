package dao;

import db.DatabaseConnection;
import models.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO {

    // Insert a new message
    public void addMessage(Message message) {
        String sql = "INSERT INTO messages (sender_id, receiver_id, content, sent_at) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, message.getSender());
            stmt.setInt(2, message.getReceiver());
            stmt.setString(3, message.getMessage());
            stmt.setTimestamp(4, Timestamp.valueOf(message.getTimestamp()));

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get messages sent to a user
    public List<Message> getMessagesForUser(int receiverId) {
        List<Message> messages = new ArrayList<>();
        String sql = "SELECT * FROM messages WHERE receiver_id = ? ORDER BY sent_at DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, receiverId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Message message = new Message(
                    rs.getInt("id"),
                    rs.getString("sender"),
                    rs.getInt("receiver_id"),
                    rs.getString("content"),
                    rs.getTimestamp("sent_at").toLocalDateTime()
                );
                messages.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }

    // You can add findById, delete, update methods as needed...
}
