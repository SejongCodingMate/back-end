package com.creativesemester.SejongCodingMate.domain.member.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberResponseDto {

    private Long storyId;
    private Boolean hasTemporaryPassword;
    private String name;


    public static MemberResponseDto of(Long storyId, Boolean hasTemporaryPassword, String name) {
        return MemberResponseDto.builder()
                .storyId(storyId)
                .hasTemporaryPassword(hasTemporaryPassword)
                .name(name)
                .build();
    }
}
