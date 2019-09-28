package com.app.dtos.request;

import lombok.Data;

@Data
public class BlogPostApprovalRequestDTO {
    private Integer postId;
    private Integer approvalStatus;
}
