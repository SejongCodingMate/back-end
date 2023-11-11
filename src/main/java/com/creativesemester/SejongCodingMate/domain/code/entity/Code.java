package com.creativesemester.SejongCodingMate.domain.code.entity;

import com.creativesemester.SejongCodingMate.domain.code.dto.request.CodeRequestDto;
import com.creativesemester.SejongCodingMate.domain.story.entity.Story;
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
    @JoinColumn(name = "STORY_ID")
    private Story story;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private String hint;

    @Column(nullable = false)
    private String answer;

    @Column(nullable = false)
    private String characterImage;

    @Column(nullable = false)
    private String itemImage;

    private String input;

    public static Code of(CodeRequestDto codeRequestDto, Story story) {
        return Code.builder()
                .story(story)
                .code(codeRequestDto.getCode())
                .hint(codeRequestDto.getHint())
                .input(codeRequestDto.getInput())
                .text(codeRequestDto.getText())
                .build();
    }
}
