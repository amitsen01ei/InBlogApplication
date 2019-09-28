package com.app.services.impl;

import com.app.constants.enums.ApprovalStatus;
import com.app.dtos.request.SubmitPostDTO;
import com.app.models.BlogPost;
import com.app.models.User;
import com.app.repositories.BlogPostRepository;
import com.app.services.BlogRequestService;
import com.app.utils.InBlogUtil;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BlogRequestServiceImpl implements BlogRequestService {

    private final InBlogUtil util;
    private final BlogPostRepository blogPostRepository;

    public BlogRequestServiceImpl(InBlogUtil util, BlogPostRepository blogPostRepository) {
        this.util = util;
        this.blogPostRepository = blogPostRepository;
    }

    @Override
    public void submitBlogPostForReview(String token, SubmitPostDTO submitPostDTO) {

        User user = util.getUserFromToken(token);

        BlogPost blogPost = new BlogPost();
        blogPost.setApprovalStatus(ApprovalStatus.PENDING.status());
        blogPost.setBody(submitPostDTO.getBody());
        blogPost.setCreatedOn(new Date());
        blogPost.setIsDeleted(false);
        blogPost.setSubmittedBy(user.getUsername());
        blogPost.setSubtitle(submitPostDTO.getSubtitle());
        blogPost.setTitle(submitPostDTO.getTitle());

        blogPostRepository.save(blogPost);
    }
}
