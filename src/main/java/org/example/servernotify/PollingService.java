package org.example.servernotify;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class PollingService {
    private final MessageStore messageStore = MessageStore.getInstance();
    private final List<PollingContext> pollingContexts = new CopyOnWriteArrayList<>();

    public List<Message> pollMessages(Long userId) {
        return messageStore.getUnreadMessages(userId);
    }

    public void sendMessage(Long userId, String content) {
        messageStore.addMessage(userId, content);
        // 检查是否有等待的长轮询请求
        notifyWaitingClient(userId);
    }

    public void markMessageAsRead(Long userId, Long messageId) {
        messageStore.markAsRead(userId, messageId);
    }

    public void addPollingContext(PollingContext context) {
        pollingContexts.add(context);
    }

    public void removePollingContext(PollingContext context) {
        pollingContexts.remove(context);
    }

    private void notifyWaitingClient(Long userId) {
        pollingContexts.stream()
            .filter(context -> context.getUserId().equals(userId))
            .forEach(context -> {
                List<Message> messages = messageStore.getUnreadMessages(userId);
                if (!messages.isEmpty()) {
                    context.getDeferredResult().setResult(messages);
                    pollingContexts.remove(context);
                }
            });
    }
}