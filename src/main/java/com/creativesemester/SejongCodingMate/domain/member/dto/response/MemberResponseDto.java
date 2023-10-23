package com.creativesemester.SejongCodingMate.domain.member.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberResponseDto {

    private Long storyId;
    private Long chapterId;
    private Boolean hasTemporaryPassword;
    private String name;


    public static MemberResponseDto of(Long storyId, Long chapterId, Boolean hasTemporaryPassword, String name) {
        return MemberResponseDto.builder()
                .storyId(storyId)
                .chapterId(chapterId)
                .hasTemporaryPassword(hasTemporaryPassword)
                .name(name)
                .build();
    }
}
