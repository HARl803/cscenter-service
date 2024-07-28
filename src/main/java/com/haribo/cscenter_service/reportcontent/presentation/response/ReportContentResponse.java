package com.haribo.cscenter_service.reportcontent.presentation.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ReportContentResponse<T> {

    private HttpStatus statusCode;
    private String resultMsg;
    private T resultData;

    // Default constructor
    public ReportContentResponse() {}

    // Constructor for creating a new UserReportContentResponse
    public ReportContentResponse(HttpStatus statusCode, String resultMsg, T resultData) {
        this.statusCode = statusCode;
        this.resultMsg = resultMsg;
        this.resultData = resultData;
    }

    // Static factory method for success response
    public static <T> ReportContentResponse<T> success(T data) {
        return new ReportContentResponse<>(HttpStatus.OK, "Success", data);
    }

    // Static factory method for error response
    public static <T> ReportContentResponse<T> error(String message, HttpStatus status) {
        return new ReportContentResponse<>(status, message, null);
    }
}
