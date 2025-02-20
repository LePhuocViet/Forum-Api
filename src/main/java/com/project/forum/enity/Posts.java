package com.project.forum.enity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.forum.enums.TypePost;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity(name = "posts")
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String type_post;

    @CreatedDate()
    @Column(updatable = false)
    LocalDateTime created_at;

    @UpdateTimestamp()
    LocalDateTime updated_at;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    Users users;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "language_id")
    Language language;

    @OneToMany(mappedBy = "posts",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    List<Likes> likes;

    @OneToMany(mappedBy = "posts",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    List<Comments> comments;

    @OneToMany(mappedBy = "posts",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    List<Advertisement> advertisements;

    @OneToOne(mappedBy = "posts", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private PostPoll postPoll;

    @OneToOne(mappedBy = "posts", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private PostContent postContent;
}
