package com.haribo.cscenter_service.inquiry.application.dto;

import com.haribo.cscenter_service.common.domain.Inquiry;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InquiryDto {

    private String inquirerId;
    private String inquiryDesc;
    private String inquiryImg;
    private LocalDateTime inquiryDate;
    private String answerInquiry;
    private LocalDateTime answerDateInquiry;

    public InquiryDto(String inquirerId, String inquiryDesc, String inquiryImg) {
        this.inquirerId = inquirerId;
        this.inquiryDesc = inquiryDesc;
        this.inquiryImg = inquiryImg;
    }

    // Factory method to create a DTO from ContentReport entity
    public static InquiryDto fromEntity(Inquiry inquiry) {
        return new InquiryDto(inquiry.getInquirer(),
                inquiry.getInquiryDesc(),
                inquiry.getInquiryImg(),
                inquiry.getInquiryDate(),
                inquiry.getAnswerInquiry(),
                inquiry.getAnswerDateInquiry());
    }
}
