package com.projectif.ooslibrary.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MemberCheckPasswordRequestDTO {

    @NotNull(message = "회원 PK는 비어있어서는 안됩니다")
    private Long memberPk;
    @NotBlank(message = "회원 비밀번호는 비어있어서는 안됩니다")
    private String password;
}
