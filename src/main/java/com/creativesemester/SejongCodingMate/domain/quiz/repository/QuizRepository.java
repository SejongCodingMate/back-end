package com.creativesemester.SejongCodingMate.domain.quiz.repository;

import com.creativesemester.SejongCodingMate.domain.quiz.entity.Quiz;
import com.creativesemester.SejongCodingMate.domain.story.entity.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository <Quiz, Long> {
    List<Quiz> findByStory(Story story);

    List<Quiz> findByStoryId(Long id);
}
