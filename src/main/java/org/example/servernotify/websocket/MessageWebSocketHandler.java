package org.example.servernotify.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.servernotify.Message;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MessageWebSocketHandler extends TextWebSocketHandler {
    private final Map<Long, WebSocketSession> userSessions = new ConcurrentHashMap<>();
    private final ObjectMapper objectMapper;

    public MessageWebSocketHandler() {
        this.objectMapper = new ObjectMapper()
            .registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule())
            .configure(com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        Long userId = extractUserId(session);
        if (userId != null) {
            userSessions.put(userId, session);
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 处理接收到的消息
        Map<String, Object> payload = objectMapper.readValue(message.getPayload(), Map.class);
        Long targetUserId = Long.valueOf(payload.get("targetUserId").toString());
        String content = payload.get("content").toString();

        // 创建消息对象
        Message newMessage = new Message(System.currentTimeMillis(), content);
        String messageJson = objectMapper.writeValueAsString(newMessage);

        // 发送消息给目标用户
        WebSocketSession targetSession = userSessions.get(targetUserId);
        if (targetSession != null && targetSession.isOpen()) {
            targetSession.sendMessage(new TextMessage(messageJson));
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) {
        Long userId = extractUserId(session);
        if (userId != null) {
            userSessions.remove(userId);
        }
    }

    private Long extractUserId(WebSocketSession session) {
        String query = session.getUri().getQuery();
        if (query != null && query.startsWith("userId=")) {
            return Long.valueOf(query.substring(7));
        }
        return null;
    }
}