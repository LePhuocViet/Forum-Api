package com.project.forum.controller;

import com.project.forum.dto.requests.comment.CreateCommentDto;
import com.project.forum.dto.responses.comment.CommentResponse;
import com.project.forum.exception.ApiResponse;
import com.project.forum.service.ICommentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/comments")
@Tag(name = "9. Comment")
public class CommentController {

    ICommentService commentService;

    @PostMapping()
    ResponseEntity<ApiResponse<CommentResponse>> create(@RequestBody() CreateCommentDto createCommentDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.<CommentResponse>builder()
                .data(commentService.create(createCommentDto))
                .build());
    }

    @GetMapping
    ResponseEntity<ApiResponse<Page<CommentResponse>>> getAll(@RequestParam(defaultValue = "0") Integer page,
                                                              @RequestParam(defaultValue = "10") Integer size,
                                                              @RequestParam("postId") String postId) {
        return ResponseEntity.ok(ApiResponse.<Page<CommentResponse>>builder()
                .data(commentService.findCommentByPost(size, page, postId))
                .build());
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ApiResponse<Boolean>> delete(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(ApiResponse.<Boolean>builder()
                        .data(commentService.deleteComment(id))
                        .build());

    }

}
