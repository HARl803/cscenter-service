package com.haribo.cscenter_service.reportcontent.application.service;

import com.haribo.cscenter_service.common.domain.ContentReport;
import com.haribo.cscenter_service.reportcontent.application.dto.ReportContentDto;
import com.haribo.cscenter_service.reportcontent.domain.repository.ReportContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportContentService {

    private final ReportContentRepository reportContentRepository;

    @Autowired
    public ReportContentService(ReportContentRepository reportContentRepository){
        this.reportContentRepository = reportContentRepository;
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

        String generatedId = generatePrimaryKey();

        ContentReport contentReport = new ContentReport(reportContentDto.getReporterIdContent(), reportContentDto.getReporteeIdContent(), reportContentDto.getOriginalIdContent());
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
