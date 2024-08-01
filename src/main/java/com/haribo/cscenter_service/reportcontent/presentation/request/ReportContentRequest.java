package com.haribo.cscenter_service.reportcontent.presentation.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReportContentRequest {
    private String reporterIdContent;
    private String reporteeIdContent;
    private String originalIdContent;
}