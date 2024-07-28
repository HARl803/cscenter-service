package com.haribo.cscenter_service.reportcontent.domain.repository;

import com.haribo.cscenter_service.common.domain.ContentReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReportContentRepository extends JpaRepository<ContentReport, String> {
    Optional<ContentReport> findByOriginalIdContent(String originalIdContent);
}
