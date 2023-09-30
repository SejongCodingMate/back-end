package com.creativesemester.SejongCodingMate.domain.dialogue.repository;

import com.creativesemester.SejongCodingMate.domain.chapter.entity.Chapter;
import com.creativesemester.SejongCodingMate.domain.dialogue.entity.Dialogue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DialogueRepository extends JpaRepository <Dialogue, Long> {
    List<Dialogue> findByStoryId(Long id);
}
