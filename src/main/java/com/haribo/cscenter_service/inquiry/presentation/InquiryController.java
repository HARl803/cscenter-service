package com.haribo.cscenter_service.inquiry.presentation;

import com.haribo.cscenter_service.inquiry.application.dto.InquiryDto;
import com.haribo.cscenter_service.inquiry.application.service.InquiryService;
import com.haribo.cscenter_service.inquiry.presentation.request.InquiryRequest;
import com.haribo.cscenter_service.inquiry.presentation.response.InquiryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/cscenter/inquiry")
public class InquiryController {

    @Autowired
    private InquiryService inquiryService;

    @PostMapping
    public ResponseEntity<InquiryResponse<InquiryDto>> submitReport(
            @RequestBody InquiryRequest request) {
        InquiryDto dto = new InquiryDto(request.getInquirerId(), request.getInquiryDesc(), request.getInquiryImg());
        InquiryDto savedReport = inquiryService.createInquiry(dto);
        //예외처리 안됨
        return ResponseEntity.ok(InquiryResponse.success(savedReport));
    }

    @PutMapping("/{inquiryId}")
    public ResponseEntity<InquiryResponse<InquiryDto>> updateInquiry(
            @PathVariable("inquiryId") String inquiryId,
            @RequestBody String answerInquiry) {
        InquiryDto updatedInquiry = inquiryService.updateInquiry(inquiryId, answerInquiry);
        return ResponseEntity.ok(InquiryResponse.success(updatedInquiry));
    }

    @GetMapping("/{inquiryId}")
    public ResponseEntity<InquiryResponse<InquiryDto>> getInquiry(
            @PathVariable("inquiryId") String inquiryId) {
        log.info("inquiryId = {}", inquiryId);
        InquiryDto dto = inquiryService.getInquiry(inquiryId);
        return ResponseEntity.ok(InquiryResponse.success(dto));
    }

    @GetMapping
    public ResponseEntity<InquiryResponse<List<InquiryDto>>> getInquiryList() {
        List<InquiryDto> dtoList = inquiryService.getInquiryList();
        return ResponseEntity.ok(InquiryResponse.success(dtoList));
    }
}