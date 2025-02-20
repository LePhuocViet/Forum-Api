package com.project.forum.service.implement;

import com.project.forum.enity.Notices;
import com.project.forum.enity.Users;
import com.project.forum.repository.NoticesRepository;
import com.project.forum.service.INoticeService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NoticeService implements INoticeService {

     final SimpMessagingTemplate messagingTemplate;
     final NoticesRepository noticesRepository;

    public void sendNotification(Users users, String type, String message, String postId) {

        if (noticesRepository.existsNoticesByTypeAndPost_idAndUser_id(type, postId, users.getId())) {
            noticesRepository.updateNoticeMessage(type,postId,users.getId(),message);
            String destination = "/user/" + users.getId() + "/queue/notifications";
            messagingTemplate.convertAndSend(destination, message);
        } else {
            Notices notice = Notices.builder()
                    .users(users)
                    .post_id(postId)
                    .type(type)
                    .message(message)
                    .status(false)
                    .build();
            noticesRepository.save(notice);

            String destination = "/user/" + users.getId() + "/queue/notifications";
            messagingTemplate.convertAndSend(destination, notice.getMessage());
        }




    }

}
