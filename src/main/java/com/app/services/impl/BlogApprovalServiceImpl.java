package com.app.services.impl;

import com.app.constants.ApplicationConstants;
import com.app.constants.ErrorMessage;
import com.app.constants.enums.ApprovalStatus;
import com.app.constants.enums.Roles;
import com.app.converters.BlogPostResponseConverter;
import com.app.converters.PageableResponseConverter;
import com.app.dtos.request.BlogPostApprovalRequestDTO;
import com.app.dtos.request.DeletePostRequestDTO;
import com.app.dtos.response.BlogPostResponseDTO;
import com.app.dtos.response.PageableResponseDTO;
import com.app.models.BlogPost;
import com.app.models.User;
import com.app.repositories.BlogPostRepository;
import com.app.services.BlogApprovalService;
import com.app.utils.InBlogResponseUtil;
import com.app.utils.InBlogUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlogApprovalServiceImpl implements BlogApprovalService {

    private final InBlogUtil util;
    private final BlogPostRepository blogPostRepository;
    private final BlogPostResponseConverter converter;

    public BlogApprovalServiceImpl(InBlogUtil util, BlogPostRepository blogPostRepository,
                                   BlogPostResponseConverter converter) {
        this.util = util;
        this.blogPostRepository = blogPostRepository;
        this.converter = converter;
    }

    @Override
    public void approveBlogPost(String token, BlogPostApprovalRequestDTO blogPostApprovalRequestDTO) {

        User user = util.getUserFromToken(token);

        if (Roles.ADMIN.getRoleId() != user.getRole()) {
            InBlogResponseUtil.throwApplicationException(ErrorMessage.UNAUTHORIZED, null);
        }

        Optional<BlogPost> optionalBlogPost = blogPostRepository.findById(blogPostApprovalRequestDTO.getPostId());

        BlogPost requestedPost = optionalBlogPost.orElseThrow(() -> InBlogResponseUtil.throwApplicationException(
                ErrorMessage.POST_NOT_FOUND, null));

        if (requestedPost.getApprovalStatus() == ApprovalStatus.APPROVED.status()) {
            InBlogResponseUtil.throwApplicationException(ErrorMessage.POST_ALREADY_APPROVED, null);
        }

        requestedPost.setApprovalStatus(ApprovalStatus.APPROVED.status());
        requestedPost.setApprovedBy(user.getUsername());

        blogPostRepository.save(requestedPost);
    }

    @Override
    public void deleteBlogPost(String token, DeletePostRequestDTO deletePostRequestDTO) {

        User user = util.getUserFromToken(token);

        if (Roles.ADMIN.getRoleId() == user.getRole()) {                                       // Admin can delete any post
            blogPostRepository.deleteById(Integer.valueOf(deletePostRequestDTO.getPostId()));
        } else {
            BlogPost blogPost = blogPostRepository.findById(Integer.valueOf(deletePostRequestDTO.getPostId()))
                    .orElseThrow(() -> InBlogResponseUtil.throwApplicationException(ErrorMessage.POST_NOT_FOUND, null));

            if (!blogPost.getSubmittedBy().equalsIgnoreCase(user.getUsername())) {             // If requester is not author, unauthorized access
                InBlogResponseUtil.throwApplicationException(ErrorMessage.UNAUTHORIZED, null);
            } else {
                blogPostRepository.delete(blogPost);                                           // Else, delete the post
            }
        }
    }

    @Override
    public PageableResponseDTO<BlogPostResponseDTO> getBlogPostsByApprovalStatus(String token, String status, Pageable pageable) {

        User user = util.getUserFromToken(token);
        Integer approvalStatus = getApprovalStatus(status);

        if (Roles.ADMIN.getRoleId() == user.getRole()) {
            return makePageableBlogPostResponse(approvalStatus, false, pageable);
        } else {
            if (ApprovalStatus.PENDING.status() == approvalStatus ||
                    ApprovalStatus.NOT_APPROVED.status() == approvalStatus) {
                InBlogResponseUtil.throwApplicationException(ErrorMessage.UNAUTHORIZED, null);
            }
        }
        return makePageableBlogPostResponse(approvalStatus, false, pageable);
    }

    private PageableResponseDTO<BlogPostResponseDTO> makePageableBlogPostResponse(Integer approvalStatus,
                                                                                  Boolean isDeleted, Pageable pageable) {

        Page<BlogPost> pagedBlogPosts = blogPostRepository.findAllByApprovalStatusAndIsDeleted(approvalStatus, isDeleted,
                pageable);

        var pageableResponseConverter = new PageableResponseConverter<BlogPost, BlogPostResponseDTO>();

        return pageableResponseConverter.convert(pagedBlogPosts, converter);
    }

    private Integer getApprovalStatus(String status) {
        switch (status) {
            case ApplicationConstants.ApprovalStatus.APPROVED:
                return ApprovalStatus.APPROVED.status();
            case ApplicationConstants.ApprovalStatus.NOT_APPROVED:
                return ApprovalStatus.NOT_APPROVED.status();
            case ApplicationConstants.ApprovalStatus.PENDING:
                return ApprovalStatus.PENDING.status();
            default:
                InBlogResponseUtil.throwApplicationException(ErrorMessage.UNKNOWN_PARAM + status, null);
        }
        return null;
    }
}
