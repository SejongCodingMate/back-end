package com.creativesemester.SejongCodingMate.domain.chapter.entity;


import com.creativesemester.SejongCodingMate.domain.course.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Chapter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "COURSE_ID")
    private Course course;

    @Column(nullable = false)
    private String name;

    public static Chapter of(Course course, String name){
        return Chapter.builder()
                .course(course)
                .name(name)
                .build();
    }

}
