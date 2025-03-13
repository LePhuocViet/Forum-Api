package com.project.forum.service;

import com.project.forum.dto.requests.friend.CreateRequestFriendDto;
import com.project.forum.dto.responses.friend.FriendRequestResponse;
import com.project.forum.dto.responses.friend.FriendShipResponse;

public interface IFriendRequestService {

    FriendRequestResponse sendRequest(CreateRequestFriendDto createRequestFriendDto);

   FriendShipResponse getFriendShip(String userId);

   boolean acceptFriendRequest(String userId);

   boolean rejectFriendRequest(String userId);

}
