package com.creativesemester.SejongCodingMate.domain.chapter.repository;

import com.creativesemester.SejongCodingMate.domain.chapter.entity.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChapterRepository extends JpaRepository <Chapter, Long> {

}