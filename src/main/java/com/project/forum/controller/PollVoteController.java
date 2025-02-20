package com.project.forum.controller;

import com.project.forum.dto.responses.vote.PollVoteResponse;
import com.project.forum.exception.ApiResponse;
import com.project.forum.service.IVoteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/poll-vote")
@Tag(name = "7. Poll Vote")
public class PollVoteController {

    IVoteService voteService;


    @PostMapping("/vote/{postPollId}")
    ResponseEntity<ApiResponse<PollVoteResponse>> vote(@PathVariable String postPollId) {
        return ResponseEntity.ok(ApiResponse.<PollVoteResponse>builder()
                .data(voteService.voteOption(postPollId))
                .build());
    }

}
