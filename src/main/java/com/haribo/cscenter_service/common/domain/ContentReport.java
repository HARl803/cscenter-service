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

    private String answerContentReport;

    @UpdateTimestamp
    private LocalDateTime answerDateContentReport;

    public ContentReport(AuthMember reportee, AuthMember reporter, String originalIdContent) {
        this.reportee = reportee;
        this.reporter = reporter;
        this.originalIdContent = originalIdContent;
    }

    // Protected no-args constructor for JPA
    protected ContentReport() {}
}
