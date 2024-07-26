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
//    @OneToMany(mappedBy = "reporter", cascade = CascadeType.ALL)
//    private List<ContentReport> reportedContents = new ArrayList<>();
//
//    @OneToMany(mappedBy = "reportee", cascade = CascadeType.ALL)
//    private List<ContentReport> receivedReports = new ArrayList<>();


//    //Method to add a content report
//    public void addContentReport(ContentReport contentReport) {
//        this.contentReports.add(contentReport);
//        contentReport.setUser(this);
//    }
}

