package org.example.servernotify;

import org.springframework.web.context.request.async.DeferredResult;
import java.util.List;

public class PollingContext {
    private final Long userId;
    private final DeferredResult<List<Message>> deferredResult;

    public PollingContext(Long userId, DeferredResult<List<Message>> deferredResult) {
        this.userId = userId;
        this.deferredResult = deferredResult;
    }

    public Long getUserId() {
        return userId;
    }

    public DeferredResult<List<Message>> getDeferredResult() {
        return deferredResult;
    }
}