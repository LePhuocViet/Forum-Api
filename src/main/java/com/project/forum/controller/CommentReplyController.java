package com.project.forum.controller;

import com.project.forum.dto.requests.comment.CreateCommentDto;
import com.project.forum.dto.requests.comment.CreateCommentReplyDto;
import com.project.forum.dto.responses.comment.CommentResponse;
import com.project.forum.exception.ApiResponse;
import com.project.forum.service.ICommentReplyService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/comment-reply")
@Tag(name = "10. Comment Reply")
public class CommentReplyController {
    ICommentReplyService commentReplyService;

    @SecurityRequirement(name = "BearerAuth")
    @PostMapping()
    ResponseEntity<ApiResponse<CommentResponse>> create(@RequestBody() CreateCommentReplyDto createCommentReplyDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.<CommentResponse>builder()
                .data(commentReplyService.create(createCommentReplyDto))
                .build());
    }
}
