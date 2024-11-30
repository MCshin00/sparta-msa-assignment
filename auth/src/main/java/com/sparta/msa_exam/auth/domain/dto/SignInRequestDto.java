package com.sparta.msa_exam.auth.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInRequestDto {
    private String username;
    private String password;
}
