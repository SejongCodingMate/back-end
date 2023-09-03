package com.creativesemester.SejongCodingMate.domain.code.entity;

import com.creativesemester.SejongCodingMate.domain.member.entity.Member;
import com.creativesemester.SejongCodingMate.domain.problem.entity.Problem;
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
public class Code {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "problem_id")
    private Problem problem;

    private String code;
    private String language;
    private String message;
    private Double score;

    public static Code of(Member member, Problem problem, String code, String language, String message, Double score) {
        return Code.builder()
                .member(member)
                .problem(problem)
                .code(code)
                .language(language)
                .message(message)
                .score(score)
                .build();
    }
}
