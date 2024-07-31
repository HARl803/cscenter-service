package com.haribo.cscenter_service.reportuser.application.service;

import com.haribo.cscenter_service.common.domain.UserReport;
import com.haribo.cscenter_service.common.domain.AuthMember;
import com.haribo.cscenter_service.reportuser.application.dto.ReportUserDto;
import com.haribo.cscenter_service.reportuser.domain.repository.AuthMemberRepositoryForReportUser;
import com.haribo.cscenter_service.reportuser.domain.repository.ReportUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportUserService {

    private final ReportUserRepository reportUserRepository;
    private final AuthMemberRepositoryForReportUser authMemberRepositoryForReportUser;

    @Autowired
    public ReportUserService(ReportUserRepository reportUserRepository, AuthMemberRepositoryForReportUser authMemberRepositoryForReportUser) {
        this.reportUserRepository = reportUserRepository;
        this.authMemberRepositoryForReportUser = authMemberRepositoryForReportUser;
    }

    public ReportUserDto getReportUser(String reportIdUser) {
        UserReport userReport = reportUserRepository.findById(reportIdUser)
                .orElseThrow(() -> new RuntimeException("Report not found"));
        return ReportUserDto.fromEntity(userReport);
    }

    public List<ReportUserDto> getReportUserList() {
        List<UserReport> reports = reportUserRepository.findAll();
        return reports.stream()
                .map(ReportUserDto::fromEntity)
                .collect(Collectors.toList());
    }

    public ReportUserDto createReport(ReportUserDto reportUserDto) {
        AuthMember reporter = authMemberRepositoryForReportUser.findById(reportUserDto.getReporterIdUser())
                .orElseThrow(() -> new RuntimeException("Reporter not found"));
        AuthMember reportee = authMemberRepositoryForReportUser.findById(reportUserDto.getReporteeIdUser())
                .orElseThrow(() -> new RuntimeException("Reportee not found"));

        String generatedId = generatePrimaryKey();

        UserReport userReport = new UserReport(reporter, reportee, reportUserDto.getReportDescUser(), reportUserDto.getReportImgUser());
        userReport.setReportIdUser(generatedId);
        reportUserRepository.save(userReport);

        return ReportUserDto.fromEntity(userReport);
    }

    public ReportUserDto updateReport(String reportIdUser, String answerReportUser) {
        UserReport userReport = reportUserRepository.findById(reportIdUser)
                .orElseThrow(() -> new RuntimeException("Report not found"));

        userReport.setAnswerReportUser(answerReportUser);
        reportUserRepository.save(userReport);

        return ReportUserDto.fromEntity(userReport);
    }

    private String generatePrimaryKey() {
        String prefix = "RPU";
        long count = reportUserRepository.count();
        String hexString = Long.toHexString(count + 1).toUpperCase();
        return prefix + String.format("%017X", Long.parseLong(hexString, 16));
    }
}