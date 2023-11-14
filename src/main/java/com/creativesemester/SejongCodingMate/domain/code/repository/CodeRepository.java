package com.creativesemester.SejongCodingMate.domain.code.repository;

import com.creativesemester.SejongCodingMate.domain.code.entity.Code;
import com.creativesemester.SejongCodingMate.domain.story.entity.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CodeRepository extends JpaRepository <Code, Long> {

    Optional<Code> findByStory(Story story);

    Code findByStoryId(Long id);
}
