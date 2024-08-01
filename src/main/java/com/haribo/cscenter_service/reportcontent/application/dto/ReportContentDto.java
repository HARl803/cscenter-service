package com.haribo.cscenter_service.reportcontent.application.dto;

import com.haribo.cscenter_service.common.domain.ContentReport;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportContentDto {
    private String reportIdContent;
    private String reporterIdContent;
    private String reporteeIdContent;
    private String originalIdContent;
    private LocalDateTime reportDateContent;
    private String answerReportContent;
    private LocalDateTime answerDateReportContent;

    public ReportContentDto(String reporterIdContent, String reporteeIdContent, String originalIdContent) {
        this.reporterIdContent = reporterIdContent;
        this.reporteeIdContent = reporteeIdContent;
        this.originalIdContent = originalIdContent;
    }

    // Factory method to create a DTO from ContentReport entity
    public static ReportContentDto fromEntity(ContentReport contentReport) {
        return new ReportContentDto(contentReport.getReportIdContent(),
                contentReport.getReporterIdContent(),
                contentReport.getReporteeIdContent(),
                contentReport.getOriginalIdContent(),
                contentReport.getReportDateContent(),
                contentReport.getAnswerReportContent(),
                contentReport.getAnswerDateReportContent());
    }
}
