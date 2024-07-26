package com.haribo.cscenter_service.user.inquiry.domain.repository;

import com.haribo.cscenter_service.common.domain.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInquiryRepository extends JpaRepository<Inquiry, String> {
}
