package services;

import models.Message;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class MessageService {
    private ArrayList<Message> messages = new ArrayList<>();
    private int nextMessageId = 1;

    public void sendMessage(String sender, String receiver, String content) {
        messages.add(new Message(nextMessageId++, sender, receiver, content, LocalDateTime.now()));
    }

    public ArrayList<Message> getMessagesForUser(String username) {
        ArrayList<Message> userMessages = new ArrayList<>();
        for (Message m : messages) {
            if (m.getSender().equals(username) || m.getReceiver().equals(username)) {
                userMessages.add(m);
            }
        }
        return userMessages;
    }
}
