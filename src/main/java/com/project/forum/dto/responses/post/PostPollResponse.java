package com.project.forum.dto.responses.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.forum.enums.TypePost;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostPollResponse {
    String id;
    String question;
    List<PollOptionResponse> pollOptions;
    TypePost typePost;
    Boolean isVoted;


}
