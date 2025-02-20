package com.project.forum.dto.requests.auth;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.antlr.v4.runtime.misc.NotNull;

@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor

public class AuthRequestDto {

    @NotNull
    String username;

    @NotNull
    String password;

}
