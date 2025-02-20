package com.project.forum.repository;

import com.project.forum.dto.responses.post.PostContentResponse;
import com.project.forum.enity.PostContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostContentRepository extends JpaRepository<PostContent, String> {

    Optional<PostContentResponse> findPostContentByPosts_Id(String postId);
}