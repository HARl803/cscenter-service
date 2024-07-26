package com.haribo.cscenter_service.user.reportuser.presentation.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class UserReportUserResponse<T> {

    private HttpStatus statusCode;
    private String resultMsg;
    private T resultData;

    // Default constructor
    public UserReportUserResponse() {}

    // Constructor for creating a new UserReportContentResponse
    public UserReportUserResponse(HttpStatus statusCode, String resultMsg, T resultData) {
        this.statusCode = statusCode;
        this.resultMsg = resultMsg;
        this.resultData = resultData;
    }

    // Static factory method for success response
    public static <T> UserReportUserResponse<T> success(T data) {
        return new UserReportUserResponse<>(HttpStatus.OK, "Success", data);
    }

    // Static factory method for error response
    public static <T> UserReportUserResponse<T> error(String message, HttpStatus status) {
        return new UserReportUserResponse<>(status, message, null);
    }
}
