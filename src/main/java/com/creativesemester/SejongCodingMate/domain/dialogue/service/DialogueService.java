package com.creativesemester.SejongCodingMate.domain.dialogue.service;

import com.creativesemester.SejongCodingMate.domain.dialogue.dto.request.DialogueRequestDto;
import com.creativesemester.SejongCodingMate.domain.dialogue.entity.Dialogue;
import com.creativesemester.SejongCodingMate.domain.dialogue.repository.DialogueRepository;
import com.creativesemester.SejongCodingMate.domain.story.entity.Story;
import com.creativesemester.SejongCodingMate.domain.story.repository.StoryRepository;
import com.creativesemester.SejongCodingMate.domain.member.entity.Member;
import com.creativesemester.SejongCodingMate.global.response.GlobalResponseDto;
import com.creativesemester.SejongCodingMate.global.response.ErrorType;
import com.creativesemester.SejongCodingMate.global.response.SuccessType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DialogueService {
    private final StoryRepository storyRepository;
    private final DialogueRepository dialogueRepository;

    // 1. 대화 생성 (POST)
    @Transactional
    public ResponseEntity<GlobalResponseDto> createDialogue(Member member, DialogueRequestDto dialogueRequestDto) {

        Optional<Story> story = storyRepository.findById(dialogueRequestDto.getStoryId());

        if (story.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body(GlobalResponseDto.of(ErrorType.STORY_NOT_FOUND));
        }

        dialogueRepository.save(Dialogue.of(dialogueRequestDto, story.get()));

        return ResponseEntity.ok(GlobalResponseDto.of(SuccessType.CHAPTER_CREATE_SUCCESS));
    }

    // 2. 대화 조회 (GET)
    @Transactional
    public ResponseEntity<GlobalResponseDto> getDialogue(Member member, Long id) {

        Optional<Dialogue> dialogue = dialogueRepository.findById(id);

        if (dialogue.isEmpty()) {
            return ResponseEntity.ok(GlobalResponseDto.of(ErrorType.NOT_VALID_REQUEST));
        }

        return ResponseEntity.ok(GlobalResponseDto.of(SuccessType.GET_CHAPTER_SUCCESS, dialogue));

    }


}
