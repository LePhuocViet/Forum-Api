package com.project.forum.service;

import com.project.forum.dto.responses.vote.PollVoteResponse;

public interface IVoteService {

    PollVoteResponse voteOption(String pollOptionId);
}
