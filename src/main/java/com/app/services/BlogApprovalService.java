package com.app.services;

import com.app.dtos.request.BlogPostApprovalRequestDTO;
import com.app.dtos.request.DeletePostRequestDTO;
import com.app.dtos.response.BlogPostResponseDTO;
import com.app.dtos.response.PageableResponseDTO;
import org.springframework.data.domain.Pageable;

public interface BlogApprovalService {

    void approveBlogPost (String token, BlogPostApprovalRequestDTO blogPostApprovalRequestDTO);

    void deleteBlogPost (String token, DeletePostRequestDTO deletePostRequestDTO);

    PageableResponseDTO<BlogPostResponseDTO> getBlogPostsByApprovalStatus (String token, String status, Pageable pageable);
}
