package com.project.forum.service;

import com.project.forum.enity.Users;

public interface INoticeService {
    void sendNotification(Users users, String type, String message, String postId);
}
