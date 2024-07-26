package com.haribo.cscenter_service.user.reportcontent.presentation.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserReportContentRequest {
    private String reporterIdContent;
    private String reporteeIdContent;
    private String originalIdContent;

    // Default constructor
    public UserReportContentRequest() {}

    // Constructor for creating a new UserReportContentRequest
    public UserReportContentRequest(String reporterIdContent, String reporteeIdContent, String originalIdContent) {
        this.reporterIdContent = reporterIdContent;
        this.reporteeIdContent = reporteeIdContent;
        this.originalIdContent = originalIdContent;
    }
}