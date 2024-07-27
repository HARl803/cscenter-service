package com.haribo.cscenter_service.inquiry.presentation.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class InquiryResponse<T> {

    private HttpStatus statusCode;
    private String resultMsg;
    private T resultData;

    // Default constructor
    public InquiryResponse() {}

    // Constructor for creating a new UserReportContentResponse
    public InquiryResponse(HttpStatus statusCode, String resultMsg, T resultData) {
        this.statusCode = statusCode;
        this.resultMsg = resultMsg;
        this.resultData = resultData;
    }

    // Static factory method for success response
    public static <T> InquiryResponse<T> success(T data) {
        return new InquiryResponse<>(HttpStatus.OK, "Success", data);
    }

    // Static factory method for error response
    public static <T> InquiryResponse<T> error(String message, HttpStatus status) {
        return new InquiryResponse<>(status, message, null);
    }
}
