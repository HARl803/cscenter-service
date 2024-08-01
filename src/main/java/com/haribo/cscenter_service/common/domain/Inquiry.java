package com.haribo.cscenter_service.common.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Inquiry {

    @Id
    @Setter
    private String inquiryId;

    @Setter
    @NotBlank
    @Column(name = "inquirer_id", nullable = false)
    private String inquirer;

    @Column(nullable = false)
    @NotBlank
    private String inquiryDesc;

    private String inquiryImg;

    @CreationTimestamp
    private LocalDateTime inquiryDate;

    @Setter
    private String answerInquiry;

    @UpdateTimestamp
    private LocalDateTime answerDateInquiry;

    public Inquiry(String inquirer, String inquiryDesc, String inquiryImg) {
        this.inquirer = inquirer;
        this.inquiryDesc = inquiryDesc;
        this.inquiryImg = inquiryImg;
    }
}
