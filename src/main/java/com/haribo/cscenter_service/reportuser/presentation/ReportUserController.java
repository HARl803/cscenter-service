package com.haribo.cscenter_service.reportuser.presentation;

import com.haribo.cscenter_service.common.presentation.request.NotificationRequest;
import com.haribo.cscenter_service.common.service.S3Service;
import com.haribo.cscenter_service.reportuser.application.dto.ReportUserDto;
import com.haribo.cscenter_service.reportuser.application.service.ReportUserService;
import com.haribo.cscenter_service.reportuser.presentation.request.ReportUserRequest;
import com.haribo.cscenter_service.reportuser.presentation.response.ReportUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cscenter/report-user")
public class ReportUserController {

    private final ReportUserService reportUserService;
    private final S3Service s3Service;

    @Autowired
    public ReportUserController(ReportUserService reportUserService, S3Service s3Service) {
        this.reportUserService = reportUserService;
        this.s3Service = s3Service;
    }

    @PostMapping
    public ResponseEntity<ReportUserResponse<ReportUserDto>> submitReport(
            @RequestPart ReportUserRequest reportUserRequest,
            @RequestPart(value = "file", required = false) MultipartFile file) {
        // S3에 이미지 파일 업로드
        String imageUrl = null;
        if (file != null && !file.isEmpty()) {
            imageUrl = s3Service.uploadFile(file);
        }

        // ReportUserDto 객체 생성
        ReportUserDto dto = new ReportUserDto(reportUserRequest.getReporterIdUser(),
                                              reportUserRequest.getReporteeIdUser(),
                                              reportUserRequest.getReportDescUser(),
                                              imageUrl);
        ReportUserDto savedReport = reportUserService.createReport(dto);

        // NotificationRequest 생성
        NotificationRequest notificationRequest = new NotificationRequest(
                "member002", //임시 관리자 아이디
                "새로운 유저 신고 건이 등록되었습니다.");

        // RestTemplate을 사용하여 /api/v1/notification으로 요청 포워딩
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Void> response = restTemplate.postForEntity(
                "http://localhost:8082/api/v1/notification", notificationRequest, Void.class);

        return ResponseEntity.ok(ReportUserResponse.success(savedReport));
    }

    @PutMapping("/{reportIdUser}")
    public ResponseEntity<ReportUserResponse<ReportUserDto>> updateReport(
            @PathVariable("reportIdUser") String reportId,
            @RequestBody String answerReportUser) {
        ReportUserDto updatedReportUser = reportUserService.updateReport(reportId, answerReportUser);

        // NotificationRequest 생성
        NotificationRequest notificationRequest = new NotificationRequest(
                updatedReportUser.getReporterIdUser(),
                "신고하신 사항에 대한 답변이 등록되었습니다. 지금 확인해보세요!");

        // RestTemplate을 사용하여 /api/v1/notification으로 요청 포워딩
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Void> response = restTemplate.postForEntity(
                "http://localhost:8082/api/v1/notification",
                notificationRequest,
                Void.class);

        return ResponseEntity.ok(ReportUserResponse.success(updatedReportUser));    }

    @GetMapping("/{reportIdUser}")
    public ResponseEntity<ReportUserResponse<ReportUserDto>> getReport(
            @PathVariable("reportIdUser") String reportIdUser) {
        ReportUserDto dto = reportUserService.getReportUser(reportIdUser);
        return ResponseEntity.ok(ReportUserResponse.success(dto));
    }

    @GetMapping
    public ResponseEntity<ReportUserResponse<List<ReportUserDto>>> getReportList() {
        List<ReportUserDto> dtoList = reportUserService.getReportUserList();
        return ResponseEntity.ok(ReportUserResponse.success(dtoList));
    }
}