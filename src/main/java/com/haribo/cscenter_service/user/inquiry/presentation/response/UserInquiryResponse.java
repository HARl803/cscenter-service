package com.haribo.cscenter_service.user.inquiry.presentation.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class UserInquiryResponse<T> {

    private HttpStatus statusCode;
    private String resultMsg;
    private T resultData;

    // Default constructor
    public UserInquiryResponse() {}

    // Constructor for creating a new UserReportContentResponse
    public UserInquiryResponse(HttpStatus statusCode, String resultMsg, T resultData) {
        this.statusCode = statusCode;
        this.resultMsg = resultMsg;
        this.resultData = resultData;
    }

    // Static factory method for success response
    public static <T> UserInquiryResponse<T> success(T data) {
        return new UserInquiryResponse<>(HttpStatus.OK, "Success", data);
    }

    // Static factory method for error response
    public static <T> UserInquiryResponse<T> error(String message, HttpStatus status) {
        return new UserInquiryResponse<>(status, message, null);
    }
}
