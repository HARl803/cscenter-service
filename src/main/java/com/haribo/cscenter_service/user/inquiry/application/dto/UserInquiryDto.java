package com.haribo.cscenter_service.user.inquiry.application.dto;

import com.haribo.cscenter_service.common.domain.Inquiry;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInquiryDto {

    private String inquirerId;
    private String inquiryDesc;
    private String inquiryImg;


    // Default constructor
    public UserInquiryDto() {}

    public UserInquiryDto(String inquirerId, String inquiryDesc, String inquiryImg) {
        this.inquirerId = inquirerId;
        this.inquiryDesc = inquiryDesc;
        this.inquiryImg = inquiryImg;
    }

    // Factory method to create a DTO from ContentReport entity
    public static UserInquiryDto fromEntity(Inquiry inquiry) {
        return new UserInquiryDto(inquiry.getInquirer().getMemberId(),
                inquiry.getInquiryDesc(),
                inquiry.getInquiryImg());
    }
}
