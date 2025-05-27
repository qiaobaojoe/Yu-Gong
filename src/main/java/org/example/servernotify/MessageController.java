package org.example.servernotify;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/messages")
@CrossOrigin(origins = "*")
public class MessageController {
    private final PollingService pollingService = new PollingService();
    private final UserService userService = UserService.getInstance();

    @PostMapping("/users")
    public Long createUser() {
        return userService.createUser();
    }

    @GetMapping("/users")
    public List<Map<String, Object>> getOnlineUsers() {
        return userService.getOnlineUsers();
    }

    @DeleteMapping("/users/{userId}")
    public void removeUser(@PathVariable Long userId) {
        userService.removeUser(userId);
    }

    @GetMapping("/{userId}")
    public List<Message> getMessages(@PathVariable Long userId) {
        return pollingService.pollMessages(userId);
    }

    @PostMapping("/{userId}/send/{targetUserId}")
    public void sendMessage(
        @PathVariable Long userId,
        @PathVariable Long targetUserId,
        @RequestBody String content
    ) {
        pollingService.sendMessage(targetUserId, content);
    }

    @PostMapping("/{userId}/messages/{messageId}")
    public void markMessageAsRead(
        @PathVariable Long userId,
        @PathVariable Long messageId
    ) {
        pollingService.markMessageAsRead(userId, messageId);
    }
}