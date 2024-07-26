package com.haribo.cscenter_service.user.reportcontent.application.dto;

import com.haribo.cscenter_service.common.domain.ContentReport;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserReportContentDto {

    private String reporterIdContent;
    private String reporteeIdContent;
    private String originalIdContent;

    // Default constructor
    public UserReportContentDto() {}

    public UserReportContentDto(String reporterId, String reporteeId, String originalIdContent) {
        this.reporterIdContent = reporterId;
        this.reporteeIdContent = reporteeId;
        this.originalIdContent = originalIdContent;
    }

    // Factory method to create a DTO from ContentReport entity
    public static UserReportContentDto fromEntity(ContentReport contentReport) {
        return new UserReportContentDto(contentReport.getReporter().getMemberId(),
                contentReport.getReportee().getMemberId(),
                contentReport.getOriginalIdContent());
    }
}
