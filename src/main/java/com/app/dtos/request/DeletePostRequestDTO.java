package com.app.dtos.request;

import lombok.Data;

@Data
public class DeletePostRequestDTO {
    private String postId;
    private String postAuthor;
}
