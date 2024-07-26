package com.haribo.cscenter_service.user.reportcontent.presentation;

import com.haribo.cscenter_service.user.reportcontent.application.dto.UserReportContentDto;
import com.haribo.cscenter_service.user.reportcontent.application.service.UserReportContentService;
import com.haribo.cscenter_service.user.reportcontent.presentation.request.UserReportContentRequest;
import com.haribo.cscenter_service.user.reportcontent.presentation.response.UserReportContentResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/cscenter/report-content")
public class UserReportContentController {

    @Autowired
    private UserReportContentService userReportContentService;

    @PostMapping
    public ResponseEntity<UserReportContentResponse<UserReportContentDto>> submitReport(
            @RequestBody UserReportContentRequest request) {
        UserReportContentDto dto = new UserReportContentDto(request.getReporterIdContent(),
                                                            request.getReporteeIdContent(),
                                                            request.getOriginalIdContent());
        UserReportContentDto savedReport = userReportContentService.createReport(dto);
        //예외처리 안됨
        return ResponseEntity.ok(UserReportContentResponse.success(savedReport));
    }

    @GetMapping("/{reportIdContent}")
    public ResponseEntity<UserReportContentResponse<UserReportContentDto>> getReportContent(
            @PathVariable("reportIdContent") String reportIdContent) {
        log.info("reportIdContent = {}", reportIdContent);
        UserReportContentDto dto = userReportContentService.getReportContent(reportIdContent);
        return ResponseEntity.ok(UserReportContentResponse.success(dto));
    }
}