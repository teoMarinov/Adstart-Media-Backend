package com.teodor.backend.controller;

import com.teodor.backend.dto.AuthResponseDto;
import com.teodor.backend.dto.BaseResponseDto;
import com.teodor.backend.dto.UserCredentialsDto;
import com.teodor.backend.entity.User;
import com.teodor.backend.service.AuthService;
import com.teodor.backend.service.JwtService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final JwtService jwtService;

    private final AuthService authService;

    public AuthController(JwtService jwtService, AuthService authService) {
        this.jwtService = jwtService;
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<BaseResponseDto<AuthResponseDto>> login(@RequestBody @Valid UserCredentialsDto loginUserDto) {
        BaseResponseDto<AuthResponseDto> baseResponseDto = new BaseResponseDto<>();

        try {
            User authenticatedUser = authService.loginUser(loginUserDto);


            String jwtToken = jwtService.generateToken(authenticatedUser);

            AuthResponseDto loginResponse = new AuthResponseDto(
                    jwtToken,
                    authenticatedUser.getUsername(),
                    authenticatedUser.getId()
            );

            baseResponseDto.setSuccessResult(loginResponse);

            return ResponseEntity.ok().body(baseResponseDto);
        } catch (AuthenticationException e) {
            baseResponseDto.setErrorMessage(e.getMessage());

            return ResponseEntity.badRequest().body(baseResponseDto);
        }
    }
}
