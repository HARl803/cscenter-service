package com.haribo.cscenter_service.inquiry.presentation.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InquiryRequest {
    private String inquirerId;
    private String inquiryDesc;
}