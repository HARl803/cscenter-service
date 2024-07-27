package com.haribo.cscenter_service.reportuser.domain.repository;

import com.haribo.cscenter_service.common.domain.AuthMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthMemberRepositoryForReportUser extends JpaRepository<AuthMember, String> {
}
