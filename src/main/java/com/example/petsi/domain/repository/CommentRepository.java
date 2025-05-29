package com.example.petsi.domain.repository;

import com.example.petsi.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByCommunityId(Long communityId);
}
