package com.creativesemester.SejongCodingMate.domain.chapter.entity;


import com.creativesemester.SejongCodingMate.domain.chapter.dto.request.ChapterRequestDto;
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

    @Column(nullable = false)
    private Long firstStoryId;

    @Column(nullable = false)
    private Long lastStoryId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String url;


    public static Chapter of(ChapterRequestDto chapterRequestDto) {
        return Chapter.builder()
                .title(chapterRequestDto.getTitle())
                .url(chapterRequestDto.getUrl())
                .firstStoryId(chapterRequestDto.getFirstStoryId())
                .lastStoryId(chapterRequestDto.getLastStoryId())
                .build();
    }

}
