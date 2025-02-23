package com.project.forum.enity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.project.forum.enums.TypePoll;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity(name = "post_poll")
public class PostPoll {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String question;

    String typePoll;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    Posts posts;

    @OneToMany(mappedBy = "postPoll",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    List<PollOptions> pollOptions;

}
