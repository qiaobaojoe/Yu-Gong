package org.example.yugong.sse;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Comment {
    private String username;
    private String content;
    private LocalDateTime timestamp;
}