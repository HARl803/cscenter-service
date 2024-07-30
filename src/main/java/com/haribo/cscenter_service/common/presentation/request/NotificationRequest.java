package com.haribo.cscenter_service.common.presentation.request;

public class NotificationRequest {
    private String userId;
    private String message;

    // 기본 생성자
    public NotificationRequest() {}

    // 생성자
    public NotificationRequest(String userId, String message) {
        this.userId = userId;
        this.message = message;
    }
}
