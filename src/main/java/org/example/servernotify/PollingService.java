package org.example.servernotify;

import java.util.List;

public class PollingService {
    private final MessageStore messageStore = MessageStore.getInstance();

    public List<Message> pollMessages(Long userId) {
        return messageStore.getUnreadMessages(userId);
    }

    public void sendMessage(Long userId, String content) {
        messageStore.addMessage(userId, content);
    }

    public void markMessageAsRead(Long userId, Long messageId) {
        messageStore.markAsRead(userId, messageId);
    }
}