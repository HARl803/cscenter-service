package com.haribo.cscenter_service.inquiry.presentation.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class InquiryRequest {
    private String inquirerId;
    private String inquiryDesc;
//    private String inquiryImg;

    // Default constructor
    public InquiryRequest() {}

    // Constructor for creating a new InquiryRequest
    public InquiryRequest(String inquirerId, String inquiryDesc) {
        this.inquirerId = inquirerId;
        this.inquiryDesc = inquiryDesc;
//        this.inquiryImg = inquiryImg;
    }
}