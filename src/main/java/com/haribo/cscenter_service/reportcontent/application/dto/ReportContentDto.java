package com.haribo.cscenter_service.reportcontent.application.dto;

import com.haribo.cscenter_service.common.domain.ContentReport;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReportContentDto {
    private String reportIdContent;
    private String reporterIdContent;
    private String reporteeIdContent;
    private String originalIdContent;
    private LocalDateTime reportDateContent;
    private String answerReportContent;
    private LocalDateTime answerDateReportContent;

    // Default constructor
    public ReportContentDto() {}

    public ReportContentDto(String reportIdContent, String reporterIdContent, String reporteeIdContent, String originalIdContent, LocalDateTime reportDateContent, String answerReportContent,
                         LocalDateTime answerDateReportContent) {
        this.reportIdContent = reportIdContent;
        this.reporterIdContent = reporterIdContent;
        this.reporteeIdContent = reporteeIdContent;
        this.originalIdContent = originalIdContent;
        this.reportDateContent = reportDateContent;
        this.answerReportContent = answerReportContent;
        this.answerDateReportContent = answerDateReportContent;
    }

    public ReportContentDto(String reporterIdContent, String reporteeIdContent, String originalIdContent) {
        this.reporterIdContent = reporterIdContent;
        this.reporteeIdContent = reporteeIdContent;
        this.originalIdContent = originalIdContent;
    }

    // Factory method to create a DTO from ContentReport entity
    public static ReportContentDto fromEntity(ContentReport contentReport) {
        return new ReportContentDto(contentReport.getReportIdContent(),
                contentReport.getReporter().getMemberId(),
                contentReport.getReportee().getMemberId(),
                contentReport.getOriginalIdContent(),
                contentReport.getReportDateContent(),
                contentReport.getAnswerReportContent(),
                contentReport.getAnswerDateReportContent());
    }
}
