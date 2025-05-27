package org.example.servernotify;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class MessageStore {
    private static final MessageStore INSTANCE = new MessageStore();
    private final ConcurrentHashMap<Long, List<Message>> userMessages = new ConcurrentHashMap<>();
    private final AtomicLong messageIdGenerator = new AtomicLong(0);

    private MessageStore() {}

    public static MessageStore getInstance() {
        return INSTANCE;
    }

    public void addMessage(Long userId, String content) {
        Message message = new Message(messageIdGenerator.incrementAndGet(), content);
        userMessages.computeIfAbsent(userId, k -> new ArrayList<>()).add(message);
    }

    public List<Message> getUnreadMessages(Long userId) {
        return userMessages.getOrDefault(userId, new ArrayList<>()).stream()
                .filter(message -> !message.isRead())
                .collect(Collectors.toList());
    }

    public void markAsRead(Long userId, Long messageId) {
        userMessages.getOrDefault(userId, new ArrayList<>()).stream()
                .filter(message -> message.getId().equals(messageId))
                .forEach(message -> message.setRead(true));
    }
}