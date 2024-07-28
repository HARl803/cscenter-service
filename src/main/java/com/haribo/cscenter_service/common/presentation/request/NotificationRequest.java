package com.haribo.cscenter_service.common.presentation.request;

import java.io.Serializable;

public class NotificationRequest implements Serializable {
    private String userId;
    private String message;

    // 기본 생성자
    public NotificationRequest() {}

    // 생성자
    public NotificationRequest(String userId, String message) {
        this.userId = userId;
        this.message = message;
    }

    // Getter와 Setter
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "NotificationRequest{" +
                "userId='" + userId + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
