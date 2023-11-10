package com.creativesemester.SejongCodingMate.domain.story.entity;

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
public class StoryLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "STORY_ID")
    private Story story;

    @Column(nullable = false)
    private Long easyId;

    @Column(nullable = false)
    private Long mediumId;

    @Column(nullable = false)
    private Long hardId;

}
