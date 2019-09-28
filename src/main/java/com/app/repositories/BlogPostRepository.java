package com.app.repositories;

import com.app.models.BlogPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Integer> {

    Page<BlogPost> findAllByApprovalStatusAndIsDeleted(@Param("status") Integer status,
                                                       @Param("isDeleted") Boolean isDeleted, Pageable pageable);
}
