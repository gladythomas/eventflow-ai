package com.eventflow_ai.repository;

import com.eventflow_ai.entity.EventPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EventPhotoRepository extends JpaRepository<EventPhoto, UUID> {

    List<EventPhoto> findAllByEventId(UUID eventId);
}
