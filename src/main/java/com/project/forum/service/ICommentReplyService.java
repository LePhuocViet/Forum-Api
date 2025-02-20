package com.project.forum.service;

import com.project.forum.dto.requests.comment.CreateCommentReplyDto;
import com.project.forum.dto.responses.comment.CommentResponse;

public interface ICommentReplyService {

    CommentResponse create(CreateCommentReplyDto createCommentReplyDto);




}
