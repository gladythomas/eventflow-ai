package com.eventflow_ai.repository;

import com.eventflow_ai.entity.PublishLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PublishLogRepository extends JpaRepository<PublishLog, UUID> {

    List<PublishLog> findAllByEventId(UUID eventId);
}
