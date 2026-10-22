package com.eventflow_ai.entity;

import com.eventflow_ai.entity.enums.PublishStatus;
import com.eventflow_ai.entity.enums.SocialPlatform;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "publish_log")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PublishLog {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private SocialPlatform platform;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    @Builder.Default
    private PublishStatus status = PublishStatus.PENDING;

    @Column(name = "platform_post_id", length = 255)
    private String platformPostId;

    @Column(name = "ai_caption", columnDefinition = "TEXT")
    private String aiCaption;

    @Column(name = "error_message", columnDefinition = "TEXT")
    private String errorMessage;

    @Column(name = "published_at")
    private LocalDateTime publishedAt;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
