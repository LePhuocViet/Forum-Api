package com.project.forum.service;

import com.project.forum.dto.requests.friend.CreateRequestFriendDto;
import com.project.forum.dto.responses.friend.FriendRequestResponse;
import com.project.forum.dto.responses.friend.FriendShipResponse;
import com.project.forum.dto.responses.user.UserFriendResponse;
import org.springframework.data.domain.Page;

public interface IFriendShipService {

    FriendRequestResponse sendRequest(CreateRequestFriendDto createRequestFriendDto);

   FriendShipResponse getFriendShip(String userId);

   boolean acceptFriendRequest(String userId);

   boolean rejectFriendRequest(String userId);

    Page<UserFriendResponse> getUserListFriend(Integer page, Integer size);
}
