package com.creativesemester.SejongCodingMate.domain.dialogue.entity;


import com.creativesemester.SejongCodingMate.domain.dialogue.dto.request.DialogueRequestDto;
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
public class Dialogue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "STORY_ID")
    private Story story;

    @Column(nullable = false)
    private String speaker;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private Long screenEffect;

    @Column(nullable = false)
    private Long soundEffect;

    @Column(nullable = false)
    private String characterImage;

    public static Dialogue of(DialogueRequestDto dialogueRequestDto, Story story) {
        return Dialogue.builder()
                .story(story)
                .speaker(dialogueRequestDto.getSpeaker())
                .text(dialogueRequestDto.getText())
                .screenEffect(dialogueRequestDto.getScreenEffect())
                .soundEffect(dialogueRequestDto.getSoundEffect())
                .characterImage(dialogueRequestDto.getCharacterImage())
                .build();
    }

}
