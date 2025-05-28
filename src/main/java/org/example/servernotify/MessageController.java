package org.example.servernotify;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.ArrayList;
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
    public DeferredResult<List<Message>> getMessages(@PathVariable Long userId) {
        DeferredResult<List<Message>> deferredResult = new DeferredResult<>(30000L, new ArrayList<>());
        
        List<Message> messages = pollingService.pollMessages(userId);
        if (!messages.isEmpty()) {
            deferredResult.setResult(messages);
            return deferredResult;
        }
        
        // 如果没有新消息，将请求挂起
        PollingContext context = new PollingContext(userId, deferredResult);
        pollingService.addPollingContext(context);
        
        deferredResult.onCompletion(() -> {
            pollingService.removePollingContext(context);
        });
        
        return deferredResult;
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