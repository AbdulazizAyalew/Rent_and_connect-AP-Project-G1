package models;
import java.time.LocalDateTime;

public class Message {
    private int messageId;
    private String sender;
    private String receiver;
    private String content;
    private LocalDateTime timestamp;

    // Constructor, Getters, Setters
    // ✅ Constructor
    public Message(int messageId, String sender, String receiver, String content, LocalDateTime timestamp) {
        this.messageId = messageId;
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.timestamp = timestamp;
    }

    // ✅ Getters
    public int getMessageId() {
        return messageId;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    // ✅ Setters (optional, in case you want to update later)
    public void setContent(String content) {
        this.content = content;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

}
