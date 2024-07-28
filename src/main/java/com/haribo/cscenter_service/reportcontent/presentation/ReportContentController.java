package com.haribo.cscenter_service.reportcontent.presentation;

import com.haribo.cscenter_service.reportcontent.application.dto.ReportContentDto;
import com.haribo.cscenter_service.reportcontent.application.service.ReportContentService;
import com.haribo.cscenter_service.reportcontent.presentation.request.ReportContentRequest;
import com.haribo.cscenter_service.reportcontent.presentation.response.ReportContentResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/cscenter/report-content")
public class ReportContentController {

    @Autowired
    private ReportContentService reportContentService;

    @PostMapping
    public ResponseEntity<ReportContentResponse<ReportContentDto>> submitReport(
            @RequestBody ReportContentRequest request) {
        ReportContentDto dto = new ReportContentDto(request.getReporterIdContent(), request.getReporteeIdContent(), request.getOriginalIdContent());
        ReportContentDto savedReport = reportContentService.createReport(dto);
        //예외처리 안됨
        return ResponseEntity.ok(ReportContentResponse.success(savedReport));
    }

    @PutMapping("/{reportIdContent}")
    public ResponseEntity<ReportContentResponse<ReportContentDto>> updateReport(
            @PathVariable("reportIdContent") String reportId,
            @RequestBody String answerReportContent) {
        ReportContentDto updatedReportContent = reportContentService.updateReport(reportId, answerReportContent);
        return ResponseEntity.ok(ReportContentResponse.success(updatedReportContent));
    }

    @GetMapping("/{reportIdContent}")
    public ResponseEntity<ReportContentResponse<ReportContentDto>> getReport(
            @PathVariable("reportIdContent") String reportIdContent) {
        log.info("reportIdContent = {}", reportIdContent);
        ReportContentDto dto = reportContentService.getReportContent(reportIdContent);
        return ResponseEntity.ok(ReportContentResponse.success(dto));
    }

    @GetMapping
    public ResponseEntity<ReportContentResponse<List<ReportContentDto>>> getReportList() {
        List<ReportContentDto> dtoList = reportContentService.getReportContentList();
        return ResponseEntity.ok(ReportContentResponse.success(dtoList));
    }
}