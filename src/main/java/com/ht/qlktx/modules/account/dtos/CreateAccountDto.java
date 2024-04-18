package com.ht.qlktx.modules.account.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAccountDto {
    private String email;
    private String password;
    private String role;
    private String username;
    @JsonProperty("user_id")
    private String userId;
}
