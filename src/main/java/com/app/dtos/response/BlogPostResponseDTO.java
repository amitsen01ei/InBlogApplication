package com.app.dtos.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BlogPostResponseDTO {
    private String title;
    private String subtitle;
    private String body;
    private String author;
    private String status;
}
