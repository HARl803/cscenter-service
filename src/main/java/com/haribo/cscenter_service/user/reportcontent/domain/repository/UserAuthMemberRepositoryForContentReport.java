package com.haribo.cscenter_service.user.reportcontent.domain.repository;

import com.haribo.cscenter_service.common.domain.AuthMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthMemberRepositoryForContentReport extends JpaRepository<AuthMember, String> {
}
