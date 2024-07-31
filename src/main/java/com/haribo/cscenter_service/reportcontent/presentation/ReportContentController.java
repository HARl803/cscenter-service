package com.haribo.cscenter_service.reportcontent.presentation;

import com.haribo.cscenter_service.common.presentation.request.NotificationRequest;
import com.haribo.cscenter_service.reportcontent.application.dto.ReportContentDto;
import com.haribo.cscenter_service.reportcontent.application.service.ReportContentService;
import com.haribo.cscenter_service.reportcontent.presentation.request.ReportContentRequest;
import com.haribo.cscenter_service.reportcontent.presentation.response.ReportContentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cscenter/report-content")
public class ReportContentController {

    private final ReportContentService reportContentService;

    @Autowired
    public ReportContentController(ReportContentService reportContentService) {
        this.reportContentService = reportContentService;
    }

    @PostMapping
    public ResponseEntity<ReportContentResponse<ReportContentDto>> submitReport(
            @RequestBody ReportContentRequest request) {
        ReportContentDto dto = new ReportContentDto(request.getReporterIdContent(), request.getReporteeIdContent(), request.getOriginalIdContent());
        ReportContentDto savedReport = reportContentService.createReport(dto);

        // NotificationRequest 생성
        NotificationRequest notificationRequest = new NotificationRequest(
                "member002", //임시 관리자 아이디
                "새로운 컨텐츠 신고 건이 등록되었습니다.");

        // RestTemplate을 사용하여 /api/v1/notification으로 요청 포워딩
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Void> response = restTemplate.postForEntity(
                "http://localhost:8082/api/v1/notification", notificationRequest, Void.class);

        return ResponseEntity.ok(ReportContentResponse.success(savedReport));
    }

    @PutMapping("/{reportIdContent}")
    public ResponseEntity<ReportContentResponse<ReportContentDto>> updateReport(
            @PathVariable("reportIdContent") String reportId,
            @RequestBody String answerReportContent) {
        ReportContentDto updatedReportContent = reportContentService.updateReport(reportId, answerReportContent);

        // NotificationRequest 생성
        NotificationRequest notificationRequest = new NotificationRequest(
                updatedReportContent.getReporterIdContent(),
                "신고하신 사항에 대한 답변이 등록되었습니다. 지금 확인해보세요!");

        // RestTemplate을 사용하여 /api/v1/notification으로 요청 포워딩
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Void> response = restTemplate.postForEntity(
                "http://localhost:8082/api/v1/notification",
                notificationRequest,
                Void.class);

        return ResponseEntity.ok(ReportContentResponse.success(updatedReportContent));
    }

    @GetMapping("/{reportIdContent}")
    public ResponseEntity<ReportContentResponse<ReportContentDto>> getReport(
            @PathVariable("reportIdContent") String reportIdContent) {
        ReportContentDto dto = reportContentService.getReportContent(reportIdContent);
        return ResponseEntity.ok(ReportContentResponse.success(dto));
    }

    @GetMapping
    public ResponseEntity<ReportContentResponse<List<ReportContentDto>>> getReportList() {
        List<ReportContentDto> dtoList = reportContentService.getReportContentList();
        return ResponseEntity.ok(ReportContentResponse.success(dtoList));
    }
}