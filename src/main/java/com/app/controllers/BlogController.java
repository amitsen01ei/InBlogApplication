package com.app.controllers;

import com.app.constants.enums.ResponseType;
import com.app.dtos.request.BlogPostApprovalRequestDTO;
import com.app.dtos.request.DeletePostRequestDTO;
import com.app.dtos.request.SubmitPostDTO;
import com.app.services.BlogApprovalService;
import com.app.services.BlogRequestService;
import com.app.utils.BaseResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/a")
public class BlogController {

    private final BlogRequestService blogRequestService;
    private final BlogApprovalService blogApprovalService;

    public BlogController(BlogRequestService blogRequestService, BlogApprovalService blogApprovalService) {
        this.blogRequestService = blogRequestService;
        this.blogApprovalService = blogApprovalService;
    }

    @PostMapping("/blog/submit-post")
    public BaseResponse submitPostForReview (@RequestHeader(name = "token") String token, @RequestBody SubmitPostDTO
                                                                                                    submitPostDTO) {
        blogRequestService.submitBlogPostForReview(token, submitPostDTO);
        return BaseResponse.builder()
                .responseType(ResponseType.RESULT)
                .message(List.of("OK"))
                .build();
    }

    @PostMapping("/blog/post-approval")
    public BaseResponse changePostApprovalStatus (@RequestHeader(name = "token") String token, @RequestBody
                                                                    BlogPostApprovalRequestDTO requestDTO) {
        blogApprovalService.approveBlogPost(token, requestDTO);
        return BaseResponse.builder()
                .responseType(ResponseType.RESULT)
                .message(List.of("OK"))
                .build();
    }

    @GetMapping("/blog/posts")
    public BaseResponse getPostsByStatus (@RequestHeader(name = "token") String token,
                                          @RequestParam(name = "status") String status, Pageable pageable) {

        return BaseResponse.builder()
                .responseType(ResponseType.RESULT)
                .message(List.of("OK"))
                .result(blogApprovalService.getBlogPostsByApprovalStatus(token, status, pageable))
                .build();
    }

    @DeleteMapping("/blog/delete")
    public BaseResponse deletePost (@RequestHeader(name = "token") String token, @RequestBody DeletePostRequestDTO
                                                                                                deletePostRequestDTO) {
        blogApprovalService.deleteBlogPost(token, deletePostRequestDTO);
        return BaseResponse.builder().build();
    }

}
