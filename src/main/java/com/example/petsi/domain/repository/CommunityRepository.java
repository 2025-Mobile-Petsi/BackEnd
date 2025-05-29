package com.example.petsi.domain.repository;

import com.example.petsi.domain.entity.Community;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityRepository extends JpaRepository<Community, Long> {
}
