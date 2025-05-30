package com.example.petsi.domain.entity;

import com.example.petsi.domain.dto.request.CreateDiaryRequestDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Diary extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diary_id")
    private Long id;

    @Column(nullable = false, length = 30)
    private String title;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String content;

    private String imageUrl;

    private String weather;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "walk_log_id")
    private WalkLog walkLog;


    @Builder
    public Diary(String title, String content, String imageUrl, String weather, User user, WalkLog walkLog) {
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.weather = weather;
        this.user = user;
        this.walkLog = walkLog;
    }

    public static Diary create(User user, WalkLog walkLog, CreateDiaryRequestDto dto) {
        return Diary.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .imageUrl(dto.getImageUrl())
                .weather(dto.getWeather())
                .user(user)
                .walkLog(walkLog)
                .build();
    }
}
