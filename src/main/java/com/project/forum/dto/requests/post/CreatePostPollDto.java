package com.project.forum.dto.requests.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.forum.enums.TypePoll;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreatePostPollDto {

    String question;

    TypePoll typePoll;

    String language;

    List<CreateOptionDto> createOptionDtoList;
}
