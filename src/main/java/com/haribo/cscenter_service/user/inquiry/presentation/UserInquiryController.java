package com.haribo.cscenter_service.user.inquiry.presentation;

import com.haribo.cscenter_service.user.inquiry.application.dto.UserInquiryDto;
import com.haribo.cscenter_service.user.inquiry.application.service.UserInquiryService;
import com.haribo.cscenter_service.user.inquiry.presentation.request.UserInquiryRequest;
import com.haribo.cscenter_service.user.inquiry.presentation.response.UserInquiryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/cscenter/inquiry")
public class UserInquiryController {

    @Autowired
    private UserInquiryService userInquiryService;

    @PostMapping
    public ResponseEntity<UserInquiryResponse<UserInquiryDto>> submitReport(
            @RequestBody UserInquiryRequest request) {
        UserInquiryDto dto = new UserInquiryDto(request.getInquirerId(),
                                                            request.getInquiryDesc(),
                                                            request.getInquiryImg());
        UserInquiryDto savedReport = userInquiryService.createInquiry(dto);
        //예외처리 안됨
        return ResponseEntity.ok(UserInquiryResponse.success(savedReport));
    }

    @GetMapping("/{inquiryId}")
    public ResponseEntity<UserInquiryResponse<UserInquiryDto>> getInquiry(
            @PathVariable("inquiryId") String inquiryId) {
        log.info("inquiryId = {}", inquiryId);
        UserInquiryDto dto = userInquiryService.getInquiry(inquiryId);
        return ResponseEntity.ok(UserInquiryResponse.success(dto));
    }
}