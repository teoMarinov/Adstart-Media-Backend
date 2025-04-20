package com.teodor.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponseDto {
    private String token;
    private String username;
    private Long id;

    public AuthResponseDto(String token, String username, Long id) {
        this.token = token;
        this.username = username;
        this.id = id;
    }
}
