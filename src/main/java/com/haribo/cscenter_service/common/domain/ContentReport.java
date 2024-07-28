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
public class ContentReport {

    @Id
    @Setter
    private String reportIdContent;

    @Setter
    @ManyToOne
    @NonNull
    @JoinColumn(name = "reporter_id_content")
    private AuthMember reporter;

    @Setter
    @ManyToOne
    @NonNull
    @JoinColumn(name = "reportee_id_content")
    private AuthMember reportee;

    @Column(nullable = false)
    @NonNull
    private String originalIdContent;

    @CreationTimestamp
    private LocalDateTime reportDateContent;

    @Setter
    private String answerReportContent;

    @UpdateTimestamp
    private LocalDateTime answerDateReportContent;

    public ContentReport(AuthMember reporter, AuthMember reportee, String originalIdContent) {
        this.reporter = reporter;
        this.reportee = reportee;
        this.originalIdContent = originalIdContent;
    }

    // Protected no-args constructor for JPA
    protected ContentReport() {}
}
