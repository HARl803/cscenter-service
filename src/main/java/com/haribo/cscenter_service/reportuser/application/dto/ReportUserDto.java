package com.haribo.cscenter_service.reportuser.application.dto;

import com.haribo.cscenter_service.common.domain.UserReport;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReportUserDto {
    private String reportIdUser;
    private String reporterIdUser;
    private String reporteeIdUser;
    private String reportDescUser;
    private String reportImgUser;
    private LocalDateTime reportDateUser;
    private String answerReportUser;
    private LocalDateTime answerDateReportUser;

    // Default constructor
    public ReportUserDto() {}

    public ReportUserDto(String reportIdUser, String reporterIdUser, String reporteeIdUser, String reportDescUser,
                         String reportImgUser, LocalDateTime reportDateUser, String answerReportUser,
                         LocalDateTime answerDateReportUser) {
        this.reportIdUser = reportIdUser;
        this.reporterIdUser = reporterIdUser;
        this.reporteeIdUser = reporteeIdUser;
        this.reportDescUser = reportDescUser;
        this.reportImgUser = reportImgUser;
        this.reportDateUser = reportDateUser;
        this.answerReportUser = answerReportUser;
        this.answerDateReportUser = answerDateReportUser;
    }

    public ReportUserDto(String reporterIdUser, String reporteeIdUser, String reportDescUser, String reportImgUser) {
        this.reporterIdUser = reporterIdUser;
        this.reporteeIdUser = reporteeIdUser;
        this.reportDescUser = reportDescUser;
        this.reportImgUser = reportImgUser;
    }

    // Factory method to create a DTO from ContentReport entity
    public static ReportUserDto fromEntity(UserReport userReport) {
        return new ReportUserDto(
                userReport.getReportIdUser(),
                userReport.getReporter().getMemberId(),
                userReport.getReportee().getMemberId(),
                userReport.getReportDescUser(),
                userReport.getReportImgUser(),
                userReport.getReportDateUser(),
                userReport.getAnswerReportUser(),
                userReport.getAnswerDateReportUser());
    }
}
