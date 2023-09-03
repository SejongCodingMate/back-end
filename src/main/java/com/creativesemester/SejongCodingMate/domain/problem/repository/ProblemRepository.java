package com.creativesemester.SejongCodingMate.domain.problem.repository;

import com.creativesemester.SejongCodingMate.domain.problem.entity.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProblemRepository extends JpaRepository <Problem, Long> {

}
