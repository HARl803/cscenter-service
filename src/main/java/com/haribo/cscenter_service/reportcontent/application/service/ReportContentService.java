package com.haribo.cscenter_service.reportcontent.application.service;

import com.haribo.cscenter_service.common.domain.ContentReport;
import com.haribo.cscenter_service.reportcontent.application.dto.ReportContentDto;
import com.haribo.cscenter_service.reportcontent.domain.repository.AuthMemberRepositoryForReportContent;
import com.haribo.cscenter_service.reportcontent.domain.repository.ReportContentRepository;
import com.haribo.cscenter_service.common.domain.AuthMember;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportContentService {

    private static final Logger log = LoggerFactory.getLogger(ReportContentService.class);
    private final ReportContentRepository reportContentRepository;
    private final AuthMemberRepositoryForReportContent authMemberRepositoryForReportContent;

    @Autowired
    public ReportContentService(ReportContentRepository reportContentRepository, AuthMemberRepositoryForReportContent authMemberRepositoryForReportContent){
        this.reportContentRepository = reportContentRepository;
        this.authMemberRepositoryForReportContent = authMemberRepositoryForReportContent;
    }

    public ReportContentDto getReportContent(String reportIdContent) {
        ContentReport contentReport = reportContentRepository.findById(reportIdContent)
                .orElseThrow(() -> new RuntimeException("Report not found"));
        return ReportContentDto.fromEntity(contentReport);
    }

    public List<ReportContentDto> getReportContentList() {
        List<ContentReport> reports = reportContentRepository.findAll();
        return reports.stream()
                .map(ReportContentDto::fromEntity)
                .collect(Collectors.toList());
    }

    public ReportContentDto createReport(ReportContentDto reportContentDto) {
        if (reportContentRepository.findByOriginalIdContent(reportContentDto.getOriginalIdContent()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Content already reported");
        }
        AuthMember reporter = authMemberRepositoryForReportContent.findById(reportContentDto.getReporterIdContent())
                .orElseThrow(() -> new RuntimeException("Reporter not found"));
        AuthMember reportee = authMemberRepositoryForReportContent.findById(reportContentDto.getReporteeIdContent())
                .orElseThrow(() -> new RuntimeException("Reportee not found"));

        String generatedId = generatePrimaryKey();

        ContentReport contentReport = new ContentReport(reporter, reportee, reportContentDto.getOriginalIdContent());
        contentReport.setReportIdContent(generatedId);
        reportContentRepository.save(contentReport);

        return ReportContentDto.fromEntity(contentReport);
    }

    public ReportContentDto updateReport(String reportIdContent, String answerReportContent) {
        ContentReport contentReport = reportContentRepository.findById(reportIdContent)
                .orElseThrow(() -> new RuntimeException("Report not found"));

        contentReport.setAnswerReportContent(answerReportContent);
        reportContentRepository.save(contentReport);

        return ReportContentDto.fromEntity(contentReport);
    }

    private String generatePrimaryKey() {
        String prefix = "RPC";
        long count = reportContentRepository.count();
        String hexString = Long.toHexString(count + 1).toUpperCase();
        return prefix + String.format("%017X", Long.parseLong(hexString, 16));
    }
}