package com.haribo.cscenter_service.user.reportcontent.presentation.response;

import org.springframework.http.HttpStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserReportContentResponse<T> {

    private HttpStatus statusCode;
    private String resultMsg;
    private T resultData;

    // Default constructor
    public UserReportContentResponse() {}

    // Constructor for creating a new UserReportContentResponse
    public UserReportContentResponse(HttpStatus statusCode, String resultMsg, T resultData) {
        this.statusCode = statusCode;
        this.resultMsg = resultMsg;
        this.resultData = resultData;
    }

    // Static factory method for success response
    public static <T> UserReportContentResponse<T> success(T data) {
        return new UserReportContentResponse<>(HttpStatus.OK, "Success", data);
    }

    // Static factory method for error response
    public static <T> UserReportContentResponse<T> error(String message, HttpStatus status) {
        return new UserReportContentResponse<>(status, message, null);
    }
}
