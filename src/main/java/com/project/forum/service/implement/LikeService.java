package com.project.forum.service.implement;

import com.project.forum.dto.requests.like.CreateLikeDto;
import com.project.forum.dto.responses.like.LikeResponse;
import com.project.forum.enity.Likes;
import com.project.forum.enity.Notices;
import com.project.forum.enity.Posts;
import com.project.forum.enity.Users;
import com.project.forum.enums.ErrorCode;
import com.project.forum.enums.TypeNotice;
import com.project.forum.enums.TypePost;
import com.project.forum.exception.WebException;
import com.project.forum.repository.LikesRepository;
import com.project.forum.repository.NoticesRepository;
import com.project.forum.repository.PostsRepository;
import com.project.forum.repository.UsersRepository;
import com.project.forum.service.ILikeService;
import com.project.forum.service.INoticeService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LikeService implements ILikeService {

    LikesRepository likesRepository;
    PostsRepository postsRepository;
    UsersRepository usersRepository;
    NoticesRepository noticesRepository;
    INoticeService noticeService;

    @Transactional
    @Override
    public LikeResponse actionLike(CreateLikeDto createLikeDto) {
        String message = "";
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Users users = usersRepository.findByUsername(username)
                .orElseThrow(() -> new WebException(ErrorCode.E_USER_NOT_FOUND));
        Posts posts = postsRepository.findById(createLikeDto.getPostId())
                .orElseThrow(() -> new WebException(ErrorCode.E_POST_NOT_FOUND));

        if (likesRepository.existsByPosts_IdAndUsers_Id(posts.getId(), users.getId())) {
            likesRepository.deleteByPosts_IdAndUsers_Id(posts.getId(), users.getId());
        } else {
            Likes likes = Likes.builder()
                    .users(users)
                    .posts(posts)
                    .build();
            likesRepository.save(likes);

            int likeCount = noticesRepository.countNoticesByTypeAndPost_id(TypeNotice.LIKE.toString(), posts.getId());

            if (noticesRepository.existsNoticesByTypeAndPost_id(TypeNotice.LIKE.toString(), posts.getId())) {
                noticesRepository.deleteNoticesByTypeAndPost_id(TypeNotice.LIKE.toString(), posts.getId());
                return LikeResponse.builder()
                        .liked(false)
                        .message("UnLike Success")
                        .build();
            }

            if (posts.getType_post().equals(TypePost.CONTENT)) {
                message = users.getName() + " and " + likeCount + " other people like your post " +
                        posts.getPostContent().getTitle().substring(0, 12) + " ...";
            } else {
                message = users.getName() + " and " + likeCount + " other people like your post " +
                        posts.getPostPoll().getQuestion().substring(0, 12) + " ...";
            }

            noticeService.sendNotification(users,TypeNotice.LIKE.toString(),message,posts.getId(),null);
        }

        return LikeResponse.builder()
                .liked(true)
                .message("Like Success")
                .build();
    }
}

