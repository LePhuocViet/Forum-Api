package com.project.forum.repository;

import com.project.forum.dto.responses.post.PostRepostResponse;
import com.project.forum.enity.PostReports;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostReportsRepository extends JpaRepository<PostReports, String> {

    @Query("SELECT NEW com.project.forum.dto.responses.post.PostRepostResponse(" +
            " pr.reason, pr.createdAt, pr.posts.id, p.type_post, p.postShow) " +
            "FROM posts p " +
            "LEFT JOIN p.postReports pr " +
            "WHERE (:postId IS NULL OR :postId = '' OR p.id = :postId)")
    Page<PostRepostResponse> getPostRepost(@Param("postId") String postId, Pageable pageable);


}