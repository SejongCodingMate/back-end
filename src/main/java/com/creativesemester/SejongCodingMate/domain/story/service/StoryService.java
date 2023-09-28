package com.creativesemester.SejongCodingMate.domain.story.service;

import com.creativesemester.SejongCodingMate.domain.chapter.entity.Chapter;
import com.creativesemester.SejongCodingMate.domain.chapter.repository.ChapterRepository;
import com.creativesemester.SejongCodingMate.domain.story.dto.request.StoryRequestDto;
import com.creativesemester.SejongCodingMate.domain.story.entity.Story;
import com.creativesemester.SejongCodingMate.domain.story.repository.StoryRepository;
import com.creativesemester.SejongCodingMate.domain.member.entity.Member;
import com.creativesemester.SejongCodingMate.global.response.GlobalResponseDto;
import com.creativesemester.SejongCodingMate.global.response.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoryService {
    private final StoryRepository storyRepository;
    private final ChapterRepository chapterRepository;

    @Transactional
    public ResponseEntity<GlobalResponseDto> createStory(Member member, StoryRequestDto storyRequestDto) {

        Optional<Chapter> chapter = chapterRepository.findById(storyRequestDto.getChapterId());
        storyRepository.save(Story.of(storyRequestDto, chapter.get()));
        return ResponseEntity.ok(GlobalResponseDto.of(ResponseCode.LOG_IN_SUCCESS));
    }

    @Transactional(readOnly = true)
    public ResponseEntity<GlobalResponseDto> getStory(Member member) {

        List<Story> story = storyRepository.findAll();
        return ResponseEntity.ok(GlobalResponseDto.of(ResponseCode.GET_COURSE_SUCCESS, story));
    }

}
