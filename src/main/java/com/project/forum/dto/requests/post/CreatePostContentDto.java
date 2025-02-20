package com.project.forum.dto.requests.post;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.forum.enity.Language;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreatePostContentDto {

    String title;

    String content;

    String img_url;

    String language;
}
