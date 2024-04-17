package com.projectif.ooslibrary.member.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmailCodeVerifyDTO {
    @NotBlank(message = "빈 코드는 유효하지 않습니다!!")
    private String code;
}
