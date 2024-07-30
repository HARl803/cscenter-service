package com.haribo.cscenter_service.inquiry.presentation.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InquiryRequest {
    private String inquirerId;
    private String inquiryDesc;

    // Default constructor
    public InquiryRequest() {}

    // Constructor for creating a new InquiryRequest
    public InquiryRequest(String inquirerId, String inquiryDesc) {
        this.inquirerId = inquirerId;
        this.inquiryDesc = inquiryDesc;
    }
}