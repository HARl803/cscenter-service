package com.haribo.cscenter_service.user.reportcontent.application.service;

import com.haribo.cscenter_service.common.domain.AuthMember;
import com.haribo.cscenter_service.common.domain.ContentReport;
import com.haribo.cscenter_service.user.reportcontent.application.dto.UserReportContentDto;
import com.haribo.cscenter_service.user.reportcontent.domain.repository.UserAuthMemberRepositoryForContentReport;
import com.haribo.cscenter_service.user.reportcontent.domain.repository.UserReportContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserReportContentService {

//    private static final Logger log = LoggerFactory.getLogger(UserReportContentService.class);
    @Autowired
    private UserReportContentRepository userReportContentRepository;

    @Autowired
    private UserAuthMemberRepositoryForContentReport userAuthMemberRepositoryForContentReport;

    public UserReportContentDto getReportContent(String reportIdContent) {
        ContentReport contentReport = userReportContentRepository.findById(reportIdContent)
                .orElseThrow(() -> new RuntimeException("Report not found"));
        return UserReportContentDto.fromEntity(contentReport);
    }

    public UserReportContentDto createReport(UserReportContentDto userReportContentDto) {
        if (userReportContentRepository.findByOriginalIdContent(userReportContentDto.getOriginalIdContent()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Content already reported");
        }

        AuthMember reporter = userAuthMemberRepositoryForContentReport.findById(userReportContentDto.getReporterIdContent())
                .orElseThrow(() -> new RuntimeException("Reporter not found"));
        AuthMember reportee = userAuthMemberRepositoryForContentReport.findById(userReportContentDto.getReporteeIdContent())
                .orElseThrow(() -> new RuntimeException("Reportee not found"));
        String generatedId = generatePrimaryKey();

        ContentReport contentReport = new ContentReport(reportee, reporter, userReportContentDto.getOriginalIdContent());
        contentReport.setReportIdContent(generatedId);
        userReportContentRepository.save(contentReport);

        return UserReportContentDto.fromEntity(contentReport);
    }

    private String generatePrimaryKey() {
        String prefix = "RPC";
        long count = userReportContentRepository.count();
        String hexString = Long.toHexString(count + 1).toUpperCase();
        return prefix + String.format("%017X", Long.parseLong(hexString, 16));
    }
}