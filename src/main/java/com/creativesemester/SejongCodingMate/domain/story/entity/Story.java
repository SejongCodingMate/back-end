package com.creativesemester.SejongCodingMate.domain.story.entity;


import com.creativesemester.SejongCodingMate.domain.chapter.entity.Chapter;
import com.creativesemester.SejongCodingMate.domain.story.dto.request.StoryRequestDto;
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
public class Story {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CHAPTER_ID")
    private Chapter chapter;

    @Column(nullable = false)
    private Long nextId;

    @Column(nullable = false)
    private Long previousId;

    @Column(nullable = false)
    private Long formatId;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String backgroundImage;

    public static Story of(StoryRequestDto storyRequestDto, Chapter chapter){
        return Story.builder()
                .nextId(storyRequestDto.getNextId())
                .previousId(storyRequestDto.getPreviousId())
                .formatId(storyRequestDto.getFormatId())
                .chapter(chapter)
                .content(storyRequestDto.getContent())
                .backgroundImage(storyRequestDto.getBackgroundImage())
                .build();
    }
}
