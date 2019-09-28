package com.app.converters;

import com.app.constants.enums.ApprovalStatus;
import com.app.dtos.response.BlogPostResponseDTO;
import com.app.models.BlogPost;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BlogPostResponseConverter implements Converter<BlogPost, BlogPostResponseDTO> {

    @Override
    public BlogPostResponseDTO convert(BlogPost source) {
        return BlogPostResponseDTO.builder()
                .author(source.getSubmittedBy())
                .body(source.getBody())
                .status(getApprovalStatus(source.getApprovalStatus()))
                .subtitle(source.getSubtitle())
                .title(source.getTitle())
                .build();
    }

    private String getApprovalStatus(Integer status) {
        switch (status) {
            case 1:
                return ApprovalStatus.PENDING.name();
            case 2:
                return ApprovalStatus.APPROVED.name();
            case 3:
                return ApprovalStatus.NOT_APPROVED.name();
            default:
                return "UNDEFINED";

        }
    }
}
