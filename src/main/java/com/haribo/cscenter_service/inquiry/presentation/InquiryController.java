package com.haribo.cscenter_service.inquiry.presentation;

import com.haribo.cscenter_service.common.service.S3Service;
import com.haribo.cscenter_service.inquiry.application.dto.InquiryDto;
import com.haribo.cscenter_service.inquiry.application.service.InquiryService;
import com.haribo.cscenter_service.inquiry.presentation.request.InquiryRequest;
import com.haribo.cscenter_service.inquiry.presentation.response.InquiryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/cscenter/inquiry")
public class InquiryController {

    @Autowired
    private InquiryService inquiryService;

    @Autowired
    private S3Service s3Service;

    @PostMapping
    public ResponseEntity<InquiryResponse<InquiryDto>> submitReport(
            @RequestPart InquiryRequest inquiryRequest,
            @RequestPart(value = "file", required = false) MultipartFile file) {
        log.info("inquiryRequest: {}", inquiryRequest.getInquirerId());
        log.info("inquiryRequest: {}", inquiryRequest.getInquiryDesc());
        log.info("inquiryImg MultipartFile type: {}", String.valueOf(file));
        // S3에 이미지 파일 업로드
        String imageUrl = null;

        if (file != null && !file.isEmpty()) {
            imageUrl = s3Service.uploadFile(file);
        }
        log.info("imageUrl: {}", imageUrl);
        // InquiryDto 객체 생성
        InquiryDto dto = new InquiryDto(inquiryRequest.getInquirerId(), inquiryRequest.getInquiryDesc(), imageUrl);
        InquiryDto savedReport = inquiryService.createInquiry(dto);

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
