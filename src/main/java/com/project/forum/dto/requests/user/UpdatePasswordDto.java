package com.project.forum.dto.requests.user;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class UpdatePasswordDto {
    String password;
    String rePassword;
}
