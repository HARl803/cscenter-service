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
public class UserReport {

    @Id
    @Setter
    private String reportIdUser;

    @Setter
    @ManyToOne
    @NonNull
    @JoinColumn(name = "reporter_id_user")
    private AuthMember reporter;

    @Setter
    @ManyToOne
    @NonNull
    @JoinColumn(name = "reportee_id_user")
    private AuthMember reportee;

    @Column(nullable = false)
    @NonNull
    private String reportDescUser;

    private String reportImgUser;

    @CreationTimestamp
    private LocalDateTime reportDateUser;

    @Setter
    private String answerReportUser;

    @UpdateTimestamp
    private LocalDateTime answerDateReportUser;

    public UserReport(AuthMember reporter, AuthMember reportee, String reportDescUser, String reportImgUser) {
        this.reporter = reporter;
        this.reportee = reportee;
        this.reportDescUser = reportDescUser;
        this.reportImgUser = reportImgUser;
    }

    // Protected no-args constructor for JPA
    protected UserReport() {}
}
