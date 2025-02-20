package com.project.forum.service.implement;

import com.project.forum.dto.requests.comment.CreateCommentReplyDto;
import com.project.forum.dto.responses.comment.CommentResponse;
import com.project.forum.enity.CommentReply;
import com.project.forum.enity.Comments;
import com.project.forum.enity.Users;
import com.project.forum.enums.ErrorCode;
import com.project.forum.exception.WebException;
import com.project.forum.repository.CommentReplyRepository;
import com.project.forum.repository.CommentsRepository;
import com.project.forum.repository.PostsRepository;
import com.project.forum.repository.UsersRepository;
import com.project.forum.service.ICommentReplyService;
import com.project.forum.service.INoticeService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentReplyService implements ICommentReplyService {

    CommentReplyRepository commentReplyRepository;

    CommentsRepository commentsRepository;

    PostsRepository postsRepository;

    UsersRepository usersRepository;

    INoticeService noticeService;

    @Override
    public CommentResponse create(CreateCommentReplyDto createCommentReplyDto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Users users = usersRepository.findByUsername(username).orElseThrow(() -> new WebException(ErrorCode.E_USER_NOT_FOUND));
        Comments comments = commentsRepository.findById(createCommentReplyDto.getCommentId()).orElseThrow(() -> new WebException(ErrorCode.E_COMMENT_NOT_FOUND));



        CommentReply commentReply = CommentReply.builder()
                .users(users)
                .comments(comments)
                .content(createCommentReplyDto.getContent())
                .build();





        return null;
    }
}
