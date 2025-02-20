package com.project.forum.mapper;

import com.project.forum.dto.responses.comment.CommentResponse;
import com.project.forum.enity.Comments;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "user_name", ignore = true)
    @Mapping(target = "user_img", ignore = true)
    @Mapping(target = "is_user", ignore = true)
    CommentResponse toCommentResponse(Comments comments);
}
