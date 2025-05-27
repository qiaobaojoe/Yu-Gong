package org.example.servernotify;

import java.time.LocalDateTime;

public class Message {
    private Long id;
    private String content;
    private LocalDateTime createTime;
    private boolean read;

    public Message(Long id, String content) {
        this.id = id;
        this.content = content;
        this.createTime = LocalDateTime.now();
        this.read = false;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public LocalDateTime getCreateTime() { return createTime; }
    public void setCreateTime(LocalDateTime createTime) { this.createTime = createTime; }
    public boolean isRead() { return read; }
    public void setRead(boolean read) { this.read = read; }
}