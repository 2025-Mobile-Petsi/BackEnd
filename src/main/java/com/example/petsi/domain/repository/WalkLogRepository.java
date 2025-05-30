package com.example.petsi.domain.repository;

import com.example.petsi.domain.entity.WalkLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WalkLogRepository extends JpaRepository<WalkLog, Long> {
    List<WalkLog> findByUserId(Long userId);
}
