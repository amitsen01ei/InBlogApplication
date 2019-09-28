package com.app.dtos.request;

import lombok.Data;

@Data
public class SubmitPostDTO {
    private String title;
    private String subtitle;
    private String body;
    private String author;
}
