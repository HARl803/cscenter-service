package com.haribo.cscenter_service.inquiry.application.dto;

import com.haribo.cscenter_service.common.domain.Inquiry;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Getter
@Setter
public class InquiryDto {

    private String inquirerId;
    private String inquiryDesc;
    private String inquiryImg;
    private LocalDateTime inquiryDate;
    private String answerInquiry;
    private LocalDateTime answerDateInquiry;

    // Default constructor
    public InquiryDto() {}

    public InquiryDto(String inquirerId, String inquiryDesc, String inquiryImg,
                      LocalDateTime inquiryDate, String answerInquiry, LocalDateTime answerDateInquiry) {
        this.inquirerId = inquirerId;
        this.inquiryDesc = inquiryDesc;
        this.inquiryImg = inquiryImg;
        this.inquiryDate = inquiryDate;
        this.answerInquiry = answerInquiry;
        this.answerDateInquiry = answerDateInquiry;
    }

    public InquiryDto(String inquirerId, String inquiryDesc, String inquiryImg) {
        this.inquirerId = inquirerId;
        this.inquiryDesc = inquiryDesc;
        this.inquiryImg = inquiryImg;
    }

    // Factory method to create a DTO from ContentReport entity
    public static InquiryDto fromEntity(Inquiry inquiry) {
        return new InquiryDto(inquiry.getInquirer().getMemberId(),
                inquiry.getInquiryDesc(),
                inquiry.getInquiryImg(),
                inquiry.getInquiryDate(),
                inquiry.getAnswerInquiry(),
                inquiry.getAnswerDateInquiry());
    }
}
