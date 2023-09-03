package com.creativesemester.SejongCodingMate.domain.problem.entity;

import com.creativesemester.SejongCodingMate.domain.chapter.entity.Chapter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Problem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "chapter_id")
    private Chapter chapter;

    private String title;
    private String content;

    @ElementCollection
    @CollectionTable(name = "problem_testcases", joinColumns = @JoinColumn(name = "problem_id"))
    @MapKeyColumn(name = "testcase_key")
    @Column(name = "testcase_value")
    private Map<String, String> testCases = new HashMap<>();

    public static Problem of(Chapter chapter, String title, String content, Map<String, String> testCases) {
        return Problem.builder()
                .chapter(chapter)
                .title(title)
                .content(content)
                .testCases(testCases)
                .build();
    }
}
