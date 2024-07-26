package com.haribo.cscenter_service.common.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
public class Inquiry {

    @Id
    @Setter
    private String inquiryId;

    @Setter
    @ManyToOne
    @NonNull
    @JoinColumn(name = "inquirer_id")
    private AuthMember inquirer;

    @Column(nullable = false)
    @NonNull
    private String inquiryDesc;

    private String inquiryImg;

    @CreationTimestamp
    private LocalDateTime inquiryDate;

    private String answerInquiry;

    @UpdateTimestamp
    private LocalDateTime answerDateInquiry;

    public Inquiry(AuthMember inquirer, String inquiryDesc, String inquiryImg) {
        this.inquirer = inquirer;
        this.inquiryDesc = inquiryDesc;
        this.inquiryImg = inquiryImg;
    }

    // Protected no-args constructor for JPA
    protected Inquiry() {}
}
