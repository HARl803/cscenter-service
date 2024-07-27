package com.haribo.cscenter_service.reportuser.domain.repository;

import com.haribo.cscenter_service.common.domain.UserReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportUserRepository extends JpaRepository<UserReport, String> {
}
