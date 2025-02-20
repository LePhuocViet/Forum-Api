package com.project.forum.enity;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.forum.enums.StatusUser;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String name;

    String language;

    String gender;

    String img;

    String email;

    String username;

    String password;

    @Enumerated(EnumType.STRING)
    StatusUser status;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST )
    Set<Roles> roles;

    @OneToMany(mappedBy = "users", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<Notices> notices;

    @OneToMany(mappedBy = "users", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<Posts> posts;

    @OneToMany(mappedBy = "users", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<Likes> likes;

    @OneToMany(mappedBy = "users", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<Comments> comments;

    @OneToMany(mappedBy = "users", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<CommentReply> commentReplies;

    @OneToMany(mappedBy = "users", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<PollVote> PollVotes;

    @OneToMany(mappedBy = "users", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<Payment> payments;

    @ManyToMany(mappedBy = "participants", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<Conversations> conversations;

    @OneToMany(mappedBy = "sender", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<Messages> messages;

    @ManyToMany
    @JoinTable(
            name = "friend_ship",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    private Set<Users> friends = new HashSet<>();

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    List<FriendRequest> sentFriendRequests;

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
    List<FriendRequest> receivedFriendRequests;
}
