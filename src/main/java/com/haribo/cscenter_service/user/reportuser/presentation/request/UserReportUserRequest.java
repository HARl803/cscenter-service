package com.haribo.cscenter_service.user.reportuser.presentation.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserReportUserRequest {
    private String reporterIdUser;
    private String reporteeIdUser;
    private String reportUserDesc;
    private String reportImgUser;

    // Default constructor
    public UserReportUserRequest() {}

    // Constructor for creating a new UserReportUserRequest
    public UserReportUserRequest(String reporterIdUser, String reporteeIdUser, String reportUserDesc, String reportImgUser) {
        this.reporterIdUser = reporterIdUser;
        this.reporteeIdUser = reporteeIdUser;
        this.reportUserDesc = reportUserDesc;
        this.reportImgUser = reportImgUser;
    }
}