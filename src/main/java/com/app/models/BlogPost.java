package com.app.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "blog_request")
@Data
@NoArgsConstructor
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Integer postId;

    @Column(name = "title")
    private String title;

    @Column(name = "subtitle")
    private String subtitle;

    @Column(name = "body")
    private String body;

    @Column(name = "approved_by")
    private String approvedBy;

    @Column(name = "submitted_by")
    private String submittedBy;

    @Column(name = "approval_status")
    private Integer approvalStatus;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "created_on")
    private Date createdOn;
}
