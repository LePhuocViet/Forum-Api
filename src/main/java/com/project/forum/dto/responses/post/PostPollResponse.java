package com.project.forum.dto.responses.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.forum.enums.TypePost;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostPollResponse {
    String id;
    String question;
    List<PollOptionResponse> pollOptions;
    String typePost;
    String postId;
    Boolean isVoted;

    // Constructor tùy chỉnh để Hibernate có thể sử dụng
    public PostPollResponse(String id, String question, String typePost, Boolean isVoted,String postId) {
        this.id = id;
        this.question = question;
        this.pollOptions = new ArrayList<>(); // Tránh null
        this.typePost = typePost;
        this.isVoted = isVoted;
        this.postId = postId;
    }
}

