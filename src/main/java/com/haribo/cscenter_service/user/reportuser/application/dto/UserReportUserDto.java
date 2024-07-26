package com.haribo.cscenter_service.user.reportuser.application.dto;

import com.haribo.cscenter_service.common.domain.UserReport;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserReportUserDto {

    private String reporterIdUser;
    private String reporteeIdUser;
    private String reportUserDesc;
    private String reportImgUser;


    // Default constructor
    public UserReportUserDto() {}

    public UserReportUserDto(String reporterId, String reporteeId, String reportDesc, String reportImg) {
        this.reporterIdUser = reporterId;
        this.reporteeIdUser = reporteeId;
        this.reportUserDesc = reportDesc;
        this.reportImgUser = reportImg;
    }

    // Factory method to create a DTO from ContentReport entity
    public static UserReportUserDto fromEntity(UserReport userReport) {
        return new UserReportUserDto(userReport.getReporter().getMemberId(),
                userReport.getReportee().getMemberId(),
                userReport.getReportDescUser(),
                userReport.getReportImgUser());
    }
}
