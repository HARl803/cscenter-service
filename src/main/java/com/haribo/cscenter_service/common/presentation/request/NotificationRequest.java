package com.haribo.cscenter_service.common.presentation.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRequest {

    @JsonProperty("userId")
    private String userId;

    @JsonProperty("message")
    private String message;
}
