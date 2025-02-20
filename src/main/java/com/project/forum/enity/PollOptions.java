package com.project.forum.enity;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@Entity(name = "poll_options")
public class PollOptions {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String option_text;



    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "poll_id")
    PostPoll postPoll;

    @OneToMany(mappedBy = "poll_options",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    List<PollVote> pollVotes;
}
