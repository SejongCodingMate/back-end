package com.creativesemester.SejongCodingMate.domain.member.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberResponseDto {

    private Long storyId;
    private Boolean hasTemporaryPassword;
    public static MemberResponseDto of(Long storyId, Boolean hasTemporaryPasswordt) {
        return MemberResponseDto.builder()
                .storyId(storyId)
                .hasTemporaryPassword(hasTemporaryPasswordt)
                .build();
    }
}
