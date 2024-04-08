package com.projectif.ooslibrary.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class EmailVerifyRequestDTO {
    @NotBlank(message = "빈 이름은 허용되지 않습니다")
    @Size(max = 100, message = "이름은 100자 이하여야 합니다.")
    private String name;
    @NotBlank(message = "빈 이메일은 허용되지 않습니다")
    @Size(max = 100, message = "이메일은 100자 이하여야 합니다.")
    private String email;
}
