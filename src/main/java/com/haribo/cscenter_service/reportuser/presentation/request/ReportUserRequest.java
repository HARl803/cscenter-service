package com.haribo.cscenter_service.reportuser.presentation.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ReportUserRequest {
    private String reporterIdUser;
    private String reporteeIdUser;
    private String reportDescUser;
    private MultipartFile reportImgUser;

    // Default constructor
    public ReportUserRequest() {}

    // Constructor for creating a new ReportUserRequest
    public ReportUserRequest(String reporterIdUser, String reporteeIdUser, String reportDescUser, MultipartFile reportImgUser) {
        this.reporterIdUser = reporterIdUser;
        this.reporteeIdUser = reporteeIdUser;
        this.reportDescUser = reportDescUser;
        this.reportImgUser = reportImgUser;
    }
}