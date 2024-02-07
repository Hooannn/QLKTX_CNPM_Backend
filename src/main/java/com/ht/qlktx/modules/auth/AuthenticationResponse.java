package com.ht.qlktx.modules.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ht.qlktx.entities.User;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private Credentials credentials;
    private User user;
}

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
class Credentials {
    @JsonProperty("access_token")
    private String accessToken;
}