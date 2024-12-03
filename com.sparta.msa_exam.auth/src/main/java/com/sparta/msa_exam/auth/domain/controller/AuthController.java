package com.sparta.msa_exam.auth.domain.controller;

import com.sparta.msa_exam.auth.domain.dto.SignUpRequestDto;
import com.sparta.msa_exam.auth.domain.dto.UsernameResponseDto;
import com.sparta.msa_exam.auth.domain.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<UsernameResponseDto> signUp(@RequestBody SignUpRequestDto requestDto) {
        UsernameResponseDto responseDto = authService.signup(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
}
