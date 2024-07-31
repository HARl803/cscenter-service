package com.haribo.cscenter_service.common.presentation.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationRequest {

    @JsonProperty("userId")
    private String userId;

    @JsonProperty("message")
    private String message;

    public NotificationRequest() {}

    public NotificationRequest(String userId, String message) {
        this.userId = userId;
        this.message = message;
    }
}