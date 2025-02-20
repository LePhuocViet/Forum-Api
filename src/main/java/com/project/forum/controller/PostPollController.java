package com.project.forum.controller;

import com.project.forum.dto.requests.post.CreatePostPollDto;
import com.project.forum.dto.responses.post.PostPollResponse;
import com.project.forum.dto.responses.post.PostResponse;
import com.project.forum.exception.ApiResponse;
import com.project.forum.service.IPostPollService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/post-poll")
@Tag(name = "5. Post Poll")
public class PostPollController {
    IPostPollService postPollService;


    @GetMapping("/{postId}")
    ResponseEntity<ApiResponse<PostPollResponse>> getById(@PathVariable String postId) {
        return ResponseEntity.ok(ApiResponse.<PostPollResponse>builder()
                .data(postPollService.findPostPollByPostId(postId))
                .build());
    }

    @PostMapping()
    ResponseEntity<ApiResponse<PostResponse>> create(@RequestBody CreatePostPollDto createPostPollDto) {
        return ResponseEntity.ok(ApiResponse.<PostResponse>builder()
                .data(postPollService.create(createPostPollDto))
                .build());
    }
}
