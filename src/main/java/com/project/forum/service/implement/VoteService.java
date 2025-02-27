package com.project.forum.service.implement;

import com.project.forum.dto.responses.vote.PollVoteResponse;
import com.project.forum.enity.*;
import com.project.forum.enums.ErrorCode;
import com.project.forum.enums.TypePoll;
import com.project.forum.exception.WebException;
import com.project.forum.repository.*;
import com.project.forum.service.IVoteService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VoteService implements IVoteService {
    PollVoteRepository pollVoteRepository;

    PostPollRepository postPollRepository;

    PollOptionsRepository pollOptionsRepository;

    PostsRepository postsRepository;

    UsersRepository usersRepository;

    @Override
    @Transactional
    public PollVoteResponse voteOption(String pollOptionId) {
        PollOptions pollOptions = pollOptionsRepository.findById(pollOptionId).orElseThrow(() -> new WebException(ErrorCode.E_POLL_OPTION_NOT_FOUND));
        PostPoll postPoll = postPollRepository.findById(pollOptions.getPostPoll().getId()).orElseThrow(() -> new WebException(ErrorCode.E_POST_POLL_NOT_FOUND));
//        Posts posts = postsRepository.findById(postPoll.getPosts().getId()).orElseThrow(() -> new WebException(ErrorCode.E_POST_NOT_FOUND));
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Users users = usersRepository.findByUsername(username).orElseThrow(() -> new WebException(ErrorCode.E_USER_NOT_FOUND));
        if (pollVoteRepository.existsVote(users.getId(), pollOptions.getId())){
            pollVoteRepository.deleteVote(users.getId(), pollOptions.getId());
            return PollVoteResponse.builder()
                    .voted(false)
                    .message("UnVote successful")
                    .build();
        }
        if (postPoll.getTypePoll().equals(TypePoll.Single)){
            if (pollVoteRepository.existsByUserIdAndPollOptionIdAndPostPollId(users.getId(),pollOptions.getId(), postPoll.getId())){
                pollVoteRepository.deleteVoteByUserIdAndPollOptionIdAndPostPollId(users.getId(),pollOptions.getId(), postPoll.getId());
            }
            PollVote newPollOptions = PollVote.builder()
                    .poll_options(pollOptions)
                    .users(users)
                    .created_at(LocalDateTime.now())
                    .build();
            pollVoteRepository.save(newPollOptions);

            return PollVoteResponse.builder()
                    .voted(true)
                    .message("Vote successful")
                    .build();
        } else {
            PollVote newPollOptions = PollVote.builder()
                    .poll_options(pollOptions)
                    .created_at(LocalDateTime.now())
                    .users(users)
                    .build();
            pollVoteRepository.save(newPollOptions);

            return PollVoteResponse.builder()
                    .voted(true)
                    .message("Vote successful")
                    .build();
        }


    }
}
