package com.haribo.cscenter_service.inquiry.application.service;

import com.haribo.cscenter_service.inquiry.application.dto.InquiryDto;
import com.haribo.cscenter_service.inquiry.domain.repository.InquiryRepository;
import com.haribo.cscenter_service.common.domain.Inquiry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InquiryService {

    private final InquiryRepository inquiryRepository;

    @Autowired
    public InquiryService(InquiryRepository inquiryRepository) {
        this.inquiryRepository = inquiryRepository;
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
        String generatedId = generatePrimaryKey();

        Inquiry inquiry = new Inquiry(inquiryDto.getInquirerId(), inquiryDto.getInquiryDesc(), inquiryDto.getInquiryImg());
        inquiry.setInquiryId(generatedId);
        inquiryRepository.save(inquiry);

        return InquiryDto.fromEntity(inquiry);
    }

    public InquiryDto updateInquiry(String inquiryId, String answerInquiry) {
        Inquiry inquiry = inquiryRepository.findById(inquiryId)
                .orElseThrow(() -> new RuntimeException("Inquiry not found"));

        inquiry.setAnswerInquiry(answerInquiry);
        inquiryRepository.save(inquiry);

        return InquiryDto.fromEntity(inquiry);
    }

    private String generatePrimaryKey() {
        String prefix = "INQ";
        long count = inquiryRepository.count();
        String hexString = Long.toHexString(count + 1).toUpperCase();
        return prefix + String.format("%017X", Long.parseLong(hexString, 16));
    }
}