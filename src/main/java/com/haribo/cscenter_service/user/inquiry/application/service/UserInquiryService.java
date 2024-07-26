package com.haribo.cscenter_service.user.inquiry.application.service;

import com.haribo.cscenter_service.common.domain.AuthMember;
import com.haribo.cscenter_service.common.domain.Inquiry;
import com.haribo.cscenter_service.user.inquiry.application.dto.UserInquiryDto;
import com.haribo.cscenter_service.user.inquiry.domain.repository.UserAuthMemberRepositoryForInquiry;
import com.haribo.cscenter_service.user.inquiry.domain.repository.UserInquiryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInquiryService {

    private static final Logger log = LoggerFactory.getLogger(UserInquiryService.class);
    @Autowired
    private UserInquiryRepository userInquiryRepository;

    @Autowired
    private UserAuthMemberRepositoryForInquiry userAuthMemberRepositoryForInquiry;

    public UserInquiryDto getInquiry(String inquiryId) {
        Inquiry inquiry = userInquiryRepository.findById(inquiryId)
                .orElseThrow(() -> new RuntimeException("Inquiry not found"));
        return UserInquiryDto.fromEntity(inquiry);
    }

    public UserInquiryDto createInquiry(UserInquiryDto userInquiryDto) {
        AuthMember inquirer = userAuthMemberRepositoryForInquiry.findById(userInquiryDto.getInquirerId())
                .orElseThrow(() -> new RuntimeException("Inquirer not found"));

        String generatedId = generatePrimaryKey();

        Inquiry inquiry = new Inquiry(inquirer, userInquiryDto.getInquiryDesc(), userInquiryDto.getInquiryImg());
        inquiry.setInquiryId(generatedId);
        userInquiryRepository.save(inquiry);

        return UserInquiryDto.fromEntity(inquiry);
    }

    private String generatePrimaryKey() {
        String prefix = "INQ";
        long count = userInquiryRepository.count();
        String hexString = Long.toHexString(count + 1).toUpperCase();
        return prefix + String.format("%017X", Long.parseLong(hexString, 16));
    }
}