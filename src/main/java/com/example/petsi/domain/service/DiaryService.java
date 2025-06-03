package com.example.petsi.domain.service;

import com.example.petsi.domain.dto.request.CreateDiaryRequestDto;
import com.example.petsi.domain.dto.response.DiaryResponseDto;
import com.example.petsi.domain.entity.Diary;
import com.example.petsi.domain.entity.User;
import com.example.petsi.domain.entity.WalkLog;
import com.example.petsi.domain.repository.DiaryRepository;
import com.example.petsi.domain.repository.UserRepository;
import com.example.petsi.domain.repository.WalkLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final UserRepository userRepository;
    private final WalkLogRepository walkLogRepository;

    public DiaryResponseDto create(CreateDiaryRequestDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new NoSuchElementException("유저가 존재하지 않습니다."));
        WalkLog walkLog = walkLogRepository.findById(dto.getWalkLogId())
                .orElseThrow(() -> new NoSuchElementException("산책 기록이 존재하지 않습니다."));

        Diary diary = Diary.create(user, walkLog, dto);
        return toResponse(diaryRepository.save(diary));
    }

    private DiaryResponseDto toResponse(Diary diary) {
        return DiaryResponseDto.builder()
                .id(diary.getId())
                .title(diary.getTitle())
                .content(diary.getContent())
                .imageUrl(diary.getImageUrl())
                .weather(diary.getWeather())
                .username(diary.getUser().getUsername())
                .build();
    }

    public DiaryResponseDto getById(Long id) {
        Diary diary = diaryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("일기장이 존재하지 않습니다."));
        return toResponse(diary);
    }

    public List<DiaryResponseDto> getByUserId(Long userId) {
        List<Diary> diaries = diaryRepository.findByUserId(userId);
        return diaries.stream().map(this::toResponse).collect(Collectors.toList());

    }
}
