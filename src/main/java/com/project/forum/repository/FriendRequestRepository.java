package com.project.forum.repository;

import com.project.forum.enity.FriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FriendRequestRepository extends JpaRepository<FriendRequest, String> {

    boolean existsByReceiver_IdAndSender_IdAndStatus(String receiverId, String senderId,String status);

    FriendRequest findByReceiver_IdAndSender_Id(String receiverId, String senderId);
}