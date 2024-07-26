package com.haribo.cscenter_service.user.reportuser.application.service;

import com.haribo.cscenter_service.common.domain.AuthMember;
import com.haribo.cscenter_service.common.domain.UserReport;
import com.haribo.cscenter_service.user.reportuser.application.dto.UserReportUserDto;
import com.haribo.cscenter_service.user.reportuser.domain.repository.UserAuthMemberRepositoryForUserReport;
import com.haribo.cscenter_service.user.reportuser.domain.repository.UserReportUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserReportUserService {

    private static final Logger log = LoggerFactory.getLogger(UserReportUserService.class);
    @Autowired
    private UserReportUserRepository userReportUserRepository;

    @Autowired
    private UserAuthMemberRepositoryForUserReport userAuthMemberRepositoryForUserReport;

    public UserReportUserDto getReportUser(String reportIdUser) {
        UserReport userReport = userReportUserRepository.findById(reportIdUser)
                .orElseThrow(() -> new RuntimeException("Report not found"));
        return UserReportUserDto.fromEntity(userReport);
    }

    public UserReportUserDto createReport(UserReportUserDto userReportUserDto) {
        AuthMember reporter = userAuthMemberRepositoryForUserReport.findById(userReportUserDto.getReporterIdUser())
                .orElseThrow(() -> new RuntimeException("Reporter not found"));
        AuthMember reportee = userAuthMemberRepositoryForUserReport.findById(userReportUserDto.getReporteeIdUser())
                .orElseThrow(() -> new RuntimeException("Reportee not found"));
        String generatedId = generatePrimaryKey();

        UserReport userReport = new UserReport(reportee, reporter,
                userReportUserDto.getReportUserDesc(), userReportUserDto.getReportImgUser());
        userReport.setReportIdUser(generatedId);
        userReportUserRepository.save(userReport);

        return UserReportUserDto.fromEntity(userReport);
    }

    private String generatePrimaryKey() {
        String prefix = "RPU";
        long count = userReportUserRepository.count();
        String hexString = Long.toHexString(count + 1).toUpperCase();
        return prefix + String.format("%017X", Long.parseLong(hexString, 16));
    }
}