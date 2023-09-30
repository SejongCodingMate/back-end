package com.creativesemester.SejongCodingMate.domain.member.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class MemberRequestDto {

    @NotBlank(message = "ID를 입력해주세요.")
    private String memberId;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

}
