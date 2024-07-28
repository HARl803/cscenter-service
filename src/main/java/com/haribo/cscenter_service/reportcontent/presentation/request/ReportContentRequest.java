package com.haribo.cscenter_service.reportcontent.presentation.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportContentRequest {
    private String reporterIdContent;
    private String reporteeIdContent;
    private String originalIdContent;

    // Default constructor
    public ReportContentRequest() {}

    // Constructor for creating a new ReportContentRequest
    public ReportContentRequest(String reporterIdContent, String reporteeIdContent, String originalIdContent) {
        this.reporterIdContent = reporterIdContent;
        this.reporteeIdContent = reporteeIdContent;
        this.originalIdContent = originalIdContent;
    }
}