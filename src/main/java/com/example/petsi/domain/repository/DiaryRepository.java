package com.example.petsi.domain.repository;

import com.example.petsi.domain.entity.Diary;
import com.example.petsi.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    List<Diary> findByUserId(Long userId);

    Long user(User user);
}
