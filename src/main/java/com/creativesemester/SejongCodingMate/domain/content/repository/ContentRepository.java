package com.creativesemester.SejongCodingMate.domain.content.repository;

import com.creativesemester.SejongCodingMate.domain.content.entity.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {

    Optional<Content> findByChapterId(Long id);

}
