package com.sparta.msa_exam.auth.domain.service;

import com.sparta.msa_exam.auth.domain.dto.SignUpRequestDto;
import com.sparta.msa_exam.auth.domain.dto.UsernameResponseDto;
import com.sparta.msa_exam.auth.model.entity.User;
import com.sparta.msa_exam.auth.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UsernameResponseDto signup(SignUpRequestDto requestDto) {
        if (userRepository.existsById(requestDto.getUsername())) {
            throw new IllegalArgumentException("이미 존재하는 username입니다.");
        }

        User user = requestDto.toEntity(passwordEncoder);
        userRepository.save(user);

        return UsernameResponseDto.builder()
                .username(user.getUsername())
                .build();
    }
}
