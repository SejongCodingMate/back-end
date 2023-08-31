package com.creativesemester.SejongCodingMate.domain.content.entity;

import com.creativesemester.SejongCodingMate.domain.chapter.entity.Chapter;
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
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CHAPTER_ID")
    private Chapter chapter;

    @Column(nullable = false)
    private String contents;

    public static Content of(Chapter chapter, String contents){
        return Content.builder()
                .chapter(chapter)
                .contents(contents)
                .build();
    }


}
