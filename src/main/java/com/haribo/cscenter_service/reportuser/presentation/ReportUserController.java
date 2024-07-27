package com.haribo.cscenter_service.reportuser.presentation;

import com.haribo.cscenter_service.reportuser.application.dto.ReportUserDto;
import com.haribo.cscenter_service.reportuser.application.service.ReportUserService;
import com.haribo.cscenter_service.reportuser.presentation.request.ReportUserRequest;
import com.haribo.cscenter_service.reportuser.presentation.response.ReportUserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/cscenter/report-user")
public class ReportUserController {

    @Autowired
    private ReportUserService reportUserService;

    @PostMapping
    public ResponseEntity<ReportUserResponse<ReportUserDto>> submitReport(
            @RequestBody ReportUserRequest request) {
        ReportUserDto dto = new ReportUserDto(request.getReporterIdUser(), request.getReporteeIdUser(), request.getReportDescUser(), request.getReportImgUser());
        ReportUserDto savedReport = reportUserService.createReport(dto);
        //예외처리 안됨
        return ResponseEntity.ok(ReportUserResponse.success(savedReport));
    }

    @PutMapping("/{reportIdUser}")
    public ResponseEntity<ReportUserResponse<ReportUserDto>> updateReport(
            @PathVariable("reportIdUser") String reportId,
            @RequestBody String answerReportUser) {
        ReportUserDto updatedReportUser = reportUserService.updateReport(reportId, answerReportUser);
        return ResponseEntity.ok(ReportUserResponse.success(updatedReportUser));
    }

    @GetMapping("/{reportIdUser}")
    public ResponseEntity<ReportUserResponse<ReportUserDto>> getReport(
            @PathVariable("reportIdUser") String reportIdUser) {
        log.info("reportIdUser = {}", reportIdUser);
        ReportUserDto dto = reportUserService.getReportUser(reportIdUser);
        return ResponseEntity.ok(ReportUserResponse.success(dto));
    }

    @GetMapping
    public ResponseEntity<ReportUserResponse<List<ReportUserDto>>> getReportList() {
        List<ReportUserDto> dtoList = reportUserService.getReportUserList();
        return ResponseEntity.ok(ReportUserResponse.success(dtoList));
    }
}