package com.example.petsi.domain.service;

import com.example.petsi.domain.dto.request.EndWalkLogRequestDto;
import com.example.petsi.domain.dto.response.WalkLogResponseDto;
import com.example.petsi.domain.entity.User;
import com.example.petsi.domain.entity.WalkLog;
import com.example.petsi.domain.repository.UserRepository;
import com.example.petsi.domain.repository.WalkLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WalkLogService {

    private final WalkLogRepository walkLogRepository;
    private final UserRepository userRepository;

    public WalkLogResponseDto startWalk(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 유저입니다."));

        WalkLog log = WalkLog.builder()
                .user(user)
                .startTime(LocalDateTime.now())
                .build();

        return toResponse(walkLogRepository.save(log));
    }

    public WalkLogResponseDto endWalk(Long walkLogId, EndWalkLogRequestDto dto) {
        WalkLog log = walkLogRepository.findById(walkLogId)
                .orElseThrow(() -> new NoSuchElementException("산책 기록이 존재하지 않습니다."));

        log.endWalk(dto.getEndTime(), dto.getDistance(), dto.getWeather());

        return toResponse(walkLogRepository.save(log));
    }

    public List<WalkLogResponseDto> getLogsByUser(Long userId) {
        return walkLogRepository.findByUserId(userId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private WalkLogResponseDto toResponse(WalkLog log) {
        return WalkLogResponseDto.builder()
                .id(log.getId())
                .username(log.getUser().getUsername())
                .startTime(log.getStartTime())
                .endTime(log.getEndTime())
                .today(LocalDate.now())
                .distance(log.getDistance())
                .weather(log.getWeather())
                .build();
    }

}
