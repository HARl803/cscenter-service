package com.haribo.cscenter_service.user.reportuser.domain.repository;

import com.haribo.cscenter_service.common.domain.AuthMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthMemberRepositoryForUserReport extends JpaRepository<AuthMember, String> {
}
