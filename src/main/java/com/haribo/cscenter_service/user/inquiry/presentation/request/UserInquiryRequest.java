package com.haribo.cscenter_service.user.inquiry.presentation.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInquiryRequest {
    private String inquirerId;
    private String inquiryDesc;
    private String inquiryImg;

    // Default constructor
    public UserInquiryRequest() {}

    // Constructor for creating a new UserInquiryRequest
    public UserInquiryRequest(String inquirerId, String inquiryDesc, String inquiryImg) {
        this.inquirerId = inquirerId;
        this.inquiryDesc = inquiryDesc;
        this.inquiryImg = inquiryImg;
    }
}