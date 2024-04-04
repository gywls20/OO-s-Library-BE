package com.projectif.ooslibrary.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MemberCheckPasswordRequestDTO {

    @NotNull(message = "회원 PK는 비어있어서는 안됩니다")
    private Long memberPk;
    private String password;
}
