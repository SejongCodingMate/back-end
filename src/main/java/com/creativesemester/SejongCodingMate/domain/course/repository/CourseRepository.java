package com.creativesemester.SejongCodingMate.domain.course.repository;

import com.creativesemester.SejongCodingMate.domain.course.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Course findByName(String courseName);
}
