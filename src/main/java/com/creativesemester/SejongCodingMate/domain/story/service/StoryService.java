package com.creativesemester.SejongCodingMate.domain.story.service;

import com.creativesemester.SejongCodingMate.domain.chapter.entity.Chapter;
import com.creativesemester.SejongCodingMate.domain.chapter.repository.ChapterRepository;
import com.creativesemester.SejongCodingMate.domain.code.entity.Code;
import com.creativesemester.SejongCodingMate.domain.code.repository.CodeRepository;
import com.creativesemester.SejongCodingMate.domain.dialogue.entity.Dialogue;
import com.creativesemester.SejongCodingMate.domain.dialogue.repository.DialogueRepository;
import com.creativesemester.SejongCodingMate.domain.member.entity.Member;
import com.creativesemester.SejongCodingMate.domain.member.repository.MemberRepository;
import com.creativesemester.SejongCodingMate.domain.quiz.repository.QuizRepository;
import com.creativesemester.SejongCodingMate.domain.story.dto.request.SaveStoryRequestDto;
import com.creativesemester.SejongCodingMate.domain.story.dto.request.StoryRequestDto;
import com.creativesemester.SejongCodingMate.domain.story.entity.Story;
import com.creativesemester.SejongCodingMate.domain.story.repository.StoryRepository;
import com.creativesemester.SejongCodingMate.global.exception.exceptionType.StoryException;
import com.creativesemester.SejongCodingMate.global.response.ErrorType;
import com.creativesemester.SejongCodingMate.global.response.GlobalResponseDto;
import com.creativesemester.SejongCodingMate.global.response.SuccessType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoryService {

    private final StoryRepository storyRepository;
    private final QuizRepository quizRepository;
    private final MemberRepository memberRepository;
    private final ChapterRepository chapterRepository;
    private final DialogueRepository dialogueRepository;
    private final CodeRepository codeRepository;


    // 1. Story 등록 (POST)
    @Transactional
    public ResponseEntity<GlobalResponseDto> createStory(Member member, StoryRequestDto storyRequestDto) {

        Optional<Chapter> chapter = chapterRepository.findById(storyRequestDto.getChapterId());
        storyRepository.save(Story.of(storyRequestDto, chapter.get()));
        return ResponseEntity.ok(GlobalResponseDto.of(SuccessType.LOG_IN_SUCCESS));
    }

    // 2. Story 전체 조회 (GET)
    @Transactional(readOnly = true)
    public ResponseEntity<GlobalResponseDto> getStoryList(Member member) {

        List<Story> story = storyRepository.findAll();
        return ResponseEntity.ok(GlobalResponseDto.of(SuccessType.GET_COURSE_SUCCESS, story));
    }

    // 3. Story 단일 조회 (GET)
    @Transactional(readOnly = true)
    public ResponseEntity<GlobalResponseDto> getStory(Member member, Long id) {

        Optional<Story> story = storyRepository.findById(id);

        if (story.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body(GlobalResponseDto.of(ErrorType.STORY_NOT_FOUND));
        }

        Long formatId = story.get().getFormatId();

        if (formatId == 1L || formatId == 2L || formatId == 3L || formatId == 4L) {
            List<Dialogue> dialogueList = dialogueRepository.findByStoryId(id);
            return ResponseEntity.ok(GlobalResponseDto.of(SuccessType.GET_COURSE_SUCCESS, dialogueList));
        }


        if (formatId == 5L) {
            Optional<Code> code = codeRepository.findByStoryId(id);
            List<Dialogue> dialogueList = dialogueRepository.findByStoryId(id);

            List<Object> codeList = new ArrayList<>();

            codeList.add(code);
            for (Dialogue d : dialogueList) {
                codeList.add(d);
            }

            return ResponseEntity.ok(GlobalResponseDto.of(SuccessType.GET_CODE_SUCCESS, codeList));
        }

        return ResponseEntity
                .badRequest()
                .body(GlobalResponseDto.of(ErrorType.STORY_NOT_FOUND));
    }

    public ResponseEntity<GlobalResponseDto> saveStory(Member member, SaveStoryRequestDto saveStoryRequestDto) {

        Optional<Story> story = storyRepository.findById(saveStoryRequestDto.getNextStoryId());

        if(story.isEmpty()){
            throw new StoryException(ErrorType.USER_NOT_FOUND);
        }

        member.changeStory(story.get());
        memberRepository.save(member);

        return ResponseEntity.ok(GlobalResponseDto.of(SuccessType.LOG_IN_SUCCESS));
    }
}
