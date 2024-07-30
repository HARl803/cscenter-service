package com.haribo.cscenter_service.common.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

@Getter
@Entity
@Immutable
public class AuthMember {

    @Id
    private String memberId;
    private String createdDate;
    private String kakaoUid;
    private String modifiedDate;

    protected AuthMember() {
        // Default constructor for JPA
    }
}

