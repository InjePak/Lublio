package com.inje.lublio.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateRequest {

    private String firstName;
    private String lastName;
    private String password;

}
