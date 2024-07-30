package com.haribo.cscenter_service.inquiry.presentation;

import com.haribo.cscenter_service.common.presentation.request.NotificationRequest;
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
import org.springframework.web.client.RestTemplate;

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
    public ResponseEntity<InquiryResponse<InquiryDto>> submitInquiry(
            @RequestPart InquiryRequest inquiryRequest,
            @RequestPart(value = "file", required = false) MultipartFile file) {
        // S3에 이미지 파일 업로드
        String imageUrl = null;
        if (file != null && !file.isEmpty()) {
            imageUrl = s3Service.uploadFile(file);
        }

        // InquiryDto 객체 생성
        InquiryDto dto = new InquiryDto(inquiryRequest.getInquirerId(), inquiryRequest.getInquiryDesc(), imageUrl);
        InquiryDto savedReport = inquiryService.createInquiry(dto);

        // NotificationRequest 생성
        NotificationRequest notificationRequest = new NotificationRequest(
                "member002", //임시 관리자 아이디
                "새로운 문의 사항이 등록되었습니다.");

        // RestTemplate을 사용하여 /api/v1/notification으로 요청 포워딩
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Void> response = restTemplate.postForEntity(
                "http://localhost:8082/api/v1/notification", notificationRequest, Void.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            log.info("Notification successfully sent");
        } else {
            log.error("Failed to send notification");
        }

        return ResponseEntity.ok(InquiryResponse.success(savedReport));
    }


    @PutMapping("/{inquiryId}")
    public ResponseEntity<InquiryResponse<InquiryDto>> updateInquiry(
            @PathVariable("inquiryId") String inquiryId,
            @RequestBody String answerInquiry) {
        InquiryDto updatedInquiry = inquiryService.updateInquiry(inquiryId, answerInquiry);

        // NotificationRequest 생성
        NotificationRequest notificationRequest = new NotificationRequest(
                updatedInquiry.getInquirerId(),
                "문의주신 사항에 대한 답변이 등록되었습니다. 지금 확인해보세요!");

        // RestTemplate을 사용하여 /api/v1/notification으로 요청 포워딩
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Void> response = restTemplate.postForEntity(
                "http://localhost:8082/api/v1/notification",
                notificationRequest,
                Void.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            log.info("Notification successfully sent");
        } else {
            log.error("Failed to send notification");
        }

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
