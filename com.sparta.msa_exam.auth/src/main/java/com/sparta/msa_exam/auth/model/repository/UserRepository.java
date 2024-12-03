package com.sparta.msa_exam.auth.model.repository;

import com.sparta.msa_exam.auth.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
