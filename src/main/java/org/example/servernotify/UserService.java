package org.example.servernotify;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class UserService {
    private static final UserService INSTANCE = new UserService();
    private final Map<Long, String> onlineUsers = new ConcurrentHashMap<>();
    private final AtomicLong userIdGenerator = new AtomicLong(1);

    private UserService() {}

    public static UserService getInstance() {
        return INSTANCE;
    }

    public Long createUser() {
        Long userId = userIdGenerator.getAndIncrement();
        onlineUsers.put(userId, "用户" + userId);
        return userId;
    }

    public void removeUser(Long userId) {
        onlineUsers.remove(userId);
    }

    public List<Map<String, Object>> getOnlineUsers() {
        return onlineUsers.entrySet().stream()
            .map(entry -> {
                Map<String, Object> user = new ConcurrentHashMap<>();
                user.put("id", entry.getKey());
                user.put("name", entry.getValue());
                return user;
            })
            .collect(Collectors.toList());
    }
}