package com.haribo.cscenter_service.common.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor // 기본 생성자
public class ContentReport {

    @Id
    @Setter
    private String reportIdContent;

    @Setter
    @NotBlank
    @Column(name = "reporter_id_content", nullable = false)
    private String reporterIdContent;

    @Setter
    @NotBlank
    @Column(name = "reportee_id_content", nullable = false)
    private String reporteeIdContent;

    @Column(nullable = false)
    @NotBlank
    private String originalIdContent;

    @CreationTimestamp
    private LocalDateTime reportDateContent;

    @Setter
    private String answerReportContent;

    @UpdateTimestamp
    private LocalDateTime answerDateReportContent;

    public ContentReport(String reporterIdContent, String reporteeIdContent, String originalIdContent) {
        this.reporterIdContent = reporterIdContent;
        this.reporteeIdContent = reporteeIdContent;
        this.originalIdContent = originalIdContent;
    }
}
