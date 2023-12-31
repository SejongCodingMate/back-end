package com.creativesemester.SejongCodingMate.domain.story.repository;

import com.creativesemester.SejongCodingMate.domain.story.entity.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StoryRepository extends JpaRepository<Story, Long> {
}
