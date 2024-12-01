package com.sparta.msa_exam.order.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class ServerPortFilter extends OncePerRequestFilter {
    @Value("${server.port}")
    private int serverPort;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 현재 서버 포트를 응답 헤더에 추가
        response.addHeader("Server-Port", String.valueOf(serverPort));

        // 필터 체인 계속 진행
        filterChain.doFilter(request, response);
    }
}
