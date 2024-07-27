package com.haribo.cscenter_service.reportuser.presentation.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ReportUserResponse<T> {

    private HttpStatus statusCode;
    private String resultMsg;
    private T resultData;

    // Default constructor
    public ReportUserResponse() {}

    // Constructor for creating a new UserReportContentResponse
    public ReportUserResponse(HttpStatus statusCode, String resultMsg, T resultData) {
        this.statusCode = statusCode;
        this.resultMsg = resultMsg;
        this.resultData = resultData;
    }

    // Static factory method for success response
    public static <T> ReportUserResponse<T> success(T data) {
        return new ReportUserResponse<>(HttpStatus.OK, "Success", data);
    }

    // Static factory method for error response
    public static <T> ReportUserResponse<T> error(String message, HttpStatus status) {
        return new ReportUserResponse<>(status, message, null);
    }
}
