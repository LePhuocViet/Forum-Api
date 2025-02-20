package com.project.forum.controller;

import com.project.forum.dto.requests.post.CreatePostContentDto;
import com.project.forum.dto.responses.post.PostContentResponse;
import com.project.forum.dto.responses.post.PostPollResponse;
import com.project.forum.dto.responses.post.PostResponse;
import com.project.forum.exception.ApiResponse;
import com.project.forum.service.IPostContentService;
import com.project.forum.service.IPostPollService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/post-content")
@Tag(name = "6. Post content")
public class PostContentController {
    IPostContentService postContentService;

    @GetMapping("/{postId}")
    ResponseEntity<ApiResponse<PostContentResponse>> getById(@PathVariable String postId) {
        return ResponseEntity.ok(ApiResponse.<PostContentResponse>builder()
                .data(postContentService.findPostContentByPostId(postId))
                .build());
    }

    @SecurityRequirement(name = "BearerAuth")
    @PostMapping()
    ResponseEntity<ApiResponse<PostResponse>> create(@RequestBody CreatePostContentDto createPostContentDto) {
        return ResponseEntity.ok(ApiResponse.<PostResponse>builder()
                .data(postContentService.create(createPostContentDto))
                .build());
    }
}
