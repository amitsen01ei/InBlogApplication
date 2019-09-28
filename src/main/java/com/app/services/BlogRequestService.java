package com.app.services;

import com.app.dtos.request.SubmitPostDTO;

public interface BlogRequestService {

    void submitBlogPostForReview (String token, SubmitPostDTO submitPostDTO);

}
