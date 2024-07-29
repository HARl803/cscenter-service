package com.haribo.cscenter_service.inquiry.application.service;

import com.haribo.cscenter_service.common.domain.AuthMember;
import com.haribo.cscenter_service.common.service.NotificationMessage;
import com.haribo.cscenter_service.common.presentation.request.NotificationRequest;
import com.haribo.cscenter_service.inquiry.application.dto.InquiryDto;
import com.haribo.cscenter_service.inquiry.domain.repository.AuthMemberRepositoryForInquiry;
import com.haribo.cscenter_service.inquiry.domain.repository.InquiryRepository;
import com.haribo.cscenter_service.common.domain.Inquiry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InquiryService {

    private static final Logger log = LoggerFactory.getLogger(InquiryService.class);

    private final InquiryRepository inquiryRepository;
    private final AuthMemberRepositoryForInquiry authMemberRepositoryForInquiry;
    private final NotificationMessage notificationMessage;

    @Autowired
    public InquiryService(InquiryRepository inquiryRepository, AuthMemberRepositoryForInquiry authMemberRepositoryForInquiry, NotificationMessage notificationMessage) {
        this.inquiryRepository = inquiryRepository;
        this.authMemberRepositoryForInquiry = authMemberRepositoryForInquiry;
        this.notificationMessage = notificationMessage;
    }

    public InquiryDto getInquiry(String inquiryId) {
        Inquiry inquiry = inquiryRepository.findById(inquiryId)
                .orElseThrow(() -> new RuntimeException("Inquiry not found"));
        return InquiryDto.fromEntity(inquiry);
    }

    public List<InquiryDto> getInquiryList() {
        List<Inquiry> inquiries = inquiryRepository.findAll();
        return inquiries.stream()
                .map(InquiryDto::fromEntity)
                .collect(Collectors.toList());
    }

    public InquiryDto createInquiry(InquiryDto inquiryDto) {
        AuthMember inquirer = authMemberRepositoryForInquiry.findById(inquiryDto.getInquirerId())
                .orElseThrow(() -> new RuntimeException("Inquirer not found"));

        String generatedId = generatePrimaryKey();

        Inquiry inquiry = new Inquiry(inquirer, inquiryDto.getInquiryDesc(), inquiryDto.getInquiryImg());
        inquiry.setInquiryId(generatedId);
        inquiryRepository.save(inquiry);

        // 관리자에게 알림 전송
        NotificationRequest notificationRequest = new NotificationRequest(
                "adminUserId", // 관리자의 유저 ID로 대체해야 함
                "새로운 문의 사항이 등록되었습니다."
        );
        notificationMessage.sendNotification(notificationRequest);

        return InquiryDto.fromEntity(inquiry);
    }

    public InquiryDto updateInquiry(String inquiryId, String answerInquiry) {
        Inquiry inquiry = inquiryRepository.findById(inquiryId)
                .orElseThrow(() -> new RuntimeException("Inquiry not found"));

        inquiry.setAnswerInquiry(answerInquiry);
        inquiryRepository.save(inquiry);

        // 유저에게 알림 전송
        NotificationRequest notificationRequest = new NotificationRequest(
                inquiry.getInquirer().getMemberId(),
                "문의 사항에 대한 답변이 등록되었습니다. 마이페이지에서 확인하세요."
        );
        notificationMessage.sendNotification(notificationRequest);

        return InquiryDto.fromEntity(inquiry);
    }

    private String generatePrimaryKey() {
        String prefix = "INQ";
        long count = inquiryRepository.count();
        String hexString = Long.toHexString(count + 1).toUpperCase();
        return prefix + String.format("%017X", Long.parseLong(hexString, 16));
    }
}