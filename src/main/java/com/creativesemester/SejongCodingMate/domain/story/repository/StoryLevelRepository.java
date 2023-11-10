package com.creativesemester.SejongCodingMate.domain.story.repository;

import com.creativesemester.SejongCodingMate.domain.story.entity.StoryLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoryLevelRepository extends JpaRepository<StoryLevel, Long> {
    StoryLevel findByStoryId(Long id);
}
