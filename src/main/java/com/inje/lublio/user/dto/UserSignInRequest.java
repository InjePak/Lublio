package com.inje.lublio.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSignInRequest {

    private String email;
    private String password;
    private String deviceToken;

}
