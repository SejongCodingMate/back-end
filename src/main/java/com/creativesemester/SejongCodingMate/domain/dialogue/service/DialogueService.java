package com.creativesemester.SejongCodingMate.domain.dialogue.service;

import com.creativesemester.SejongCodingMate.domain.chapter.dto.request.ChapterRequestDto;
import com.creativesemester.SejongCodingMate.domain.chapter.entity.Chapter;
import com.creativesemester.SejongCodingMate.domain.chapter.repository.ChapterRepository;
import com.creativesemester.SejongCodingMate.domain.dialogue.dto.request.DialogueRequestDto;
import com.creativesemester.SejongCodingMate.domain.dialogue.entity.Dialogue;
import com.creativesemester.SejongCodingMate.domain.dialogue.repository.DialogueRepository;
import com.creativesemester.SejongCodingMate.domain.story.entity.Story;
import com.creativesemester.SejongCodingMate.domain.story.repository.StoryRepository;
import com.creativesemester.SejongCodingMate.domain.member.entity.Member;
import com.creativesemester.SejongCodingMate.global.response.GlobalResponseDto;
import com.creativesemester.SejongCodingMate.global.response.ResponseCode;
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

    // 1. Chapter 생성 (POST)
    @Transactional
    public ResponseEntity<GlobalResponseDto> createDialogue(Member member, DialogueRequestDto dialogueRequestDto) {

        Optional <Story> story = storyRepository.findById(dialogueRequestDto.getDialogueId());
        dialogueRepository.save(Dialogue.of(dialogueRequestDto,story.get()));
        return ResponseEntity.ok(GlobalResponseDto.of(ResponseCode.CHAPTER_CREATE_SUCCESS));
    }

    // 2. Chapter 조회 (GET)
    @Transactional
    public ResponseEntity<GlobalResponseDto> getDialogue(Member member, Long id) {

        Optional <Dialogue> dialogue= dialogueRepository.findById(id);

        if(dialogue.isEmpty()){
            return ResponseEntity.ok(GlobalResponseDto.of(ResponseCode.NOT_VALID_REQUEST));
        }

        return ResponseEntity.ok(GlobalResponseDto.of(ResponseCode.GET_CHAPTER_SUCCESS,dialogue));

    }


}
