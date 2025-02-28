package com.project.forum.controller;

import com.project.forum.dto.requests.vote.CreateVoteMultipleDto;
import com.project.forum.dto.responses.vote.PollVoteResponse;
import com.project.forum.exception.ApiResponse;
import com.project.forum.service.IVoteService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/poll-vote")
@Tag(name = "7. Poll Vote")
public class PollVoteController {

    IVoteService voteService;

    @SecurityRequirement(name = "BearerAuth")
    @PostMapping("/vote/{postPollId}")
    ResponseEntity<ApiResponse<PollVoteResponse>> vote(@PathVariable String postPollId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.<PollVoteResponse>builder()
                .data(voteService.voteOption(postPollId))
                .build());
    }

    @SecurityRequirement(name = "BearerAuth")
    @PostMapping("/vote/multiple")
    ResponseEntity<ApiResponse<PollVoteResponse>> voteMultiple(@RequestBody CreateVoteMultipleDto createVoteMultipleDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.<PollVoteResponse>builder()
                .data(voteService.voteOptionMultiple(createVoteMultipleDto))
                .build());
    }

}
