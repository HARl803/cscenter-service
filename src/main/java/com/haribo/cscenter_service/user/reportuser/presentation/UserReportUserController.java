package com.haribo.cscenter_service.user.reportuser.presentation;

import com.haribo.cscenter_service.user.reportuser.application.dto.UserReportUserDto;
import com.haribo.cscenter_service.user.reportuser.application.service.UserReportUserService;
import com.haribo.cscenter_service.user.reportuser.presentation.request.UserReportUserRequest;
import com.haribo.cscenter_service.user.reportuser.presentation.response.UserReportUserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/cscenter/report-user")
public class UserReportUserController {

    @Autowired
    private UserReportUserService userReportUserService;

    @PostMapping
    public ResponseEntity<UserReportUserResponse<UserReportUserDto>> submitReport(
            @RequestBody UserReportUserRequest request) {
        UserReportUserDto dto = new UserReportUserDto(request.getReporterIdUser(),
                                                            request.getReporteeIdUser(),
                                                            request.getReportUserDesc(),
                                                            request.getReportImgUser());
        UserReportUserDto savedReport = userReportUserService.createReport(dto);
        //예외처리 안됨
        return ResponseEntity.ok(UserReportUserResponse.success(savedReport));
    }

    @GetMapping("/{reportIdUser}")
    public ResponseEntity<UserReportUserResponse<UserReportUserDto>> getReportUser(
            @PathVariable("reportIdUser") String reportIdUser) {
        log.info("reportIdUser = {}", reportIdUser);
        UserReportUserDto dto = userReportUserService.getReportUser(reportIdUser);
        return ResponseEntity.ok(UserReportUserResponse.success(dto));
    }
}