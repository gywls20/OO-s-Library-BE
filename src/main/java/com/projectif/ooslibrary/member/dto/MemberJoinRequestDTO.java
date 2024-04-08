package com.projectif.ooslibrary.member.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberJoinRequestDTO {
    /**
     * 회원 가입 요청 DTO
     */
    @NotBlank(message = "빈 아이디는 허용되지 않습니다")
    @Email(message = "아이디를 이메일 형식으로 적으세요")
    @Size(max = 50, message = "아이디는 50자 이하여야 합니다.")
    private String memberId;
    @NotBlank(message = "빈 이름은 허용되지 않습니다")
    @Size(max = 50, message = "이름은 50자 이하여야 합니다.")
    private String memberName;
    @NotBlank(message = "빈 이메일은 허용되지 않습니다")
    @Size(max = 50, message = "이메일은 50자 이하여야 합니다.")
    @Email(message = "이메일은 이메일 형식으로 적으세요")
    private String memberEmail;
    @Size(max = 100)
    private String memberPassword;
    @NotNull(message = "성별을 골라주세요")
    @Max(value = 2, message = "성별을 제대로 골라주세요")
    @Min(value = 0, message = "성별을 제대로 골라주세요")
    private Integer memberGender;

}
