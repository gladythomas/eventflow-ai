package com.eventflow_ai.repository;

import com.eventflow_ai.entity.SocialAccount;
import com.eventflow_ai.entity.enums.SocialPlatform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SocialAccountRepository extends JpaRepository<SocialAccount, UUID> {

    Optional<SocialAccount> findByUserIdAndPlatform(UUID userId, SocialPlatform platform);

    List<SocialAccount> findAllByUserId(UUID userId);

    boolean existsByUserIdAndPlatform(UUID userId, SocialPlatform platform);
}
