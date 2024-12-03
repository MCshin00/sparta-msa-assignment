package com.sparta.msa_exam.auth.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UsernameResponseDto {
    private String username;
}
