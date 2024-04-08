package com.projectif.ooslibrary.member.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberUpdateRequestDTO {
    /**
     * 회원 수정 요청 DTO
     * - PK값 : member_pk
     * - 아이디 : member_id
     * - 이름 : member_name
     * - 이메일 : member_email
     * - 비밀번호 : member_password
     * - 성별 : member_gender
     * - 프로필 사진 : member_profile
     */
    private Long memberPk;
    @NotBlank(message = "빈 이름은 허용되지 않습니다")
    @Size(max = 50, message = "이름은 50자 이하여야 합니다.")
    private String memberName;
    @NotBlank(message = "빈 이메일은 허용되지 않습니다")
    @Size(max = 50, message = "이메일은 50자 이하여야 합니다.")
    @Email(message = "이메일 형식으로 적으세요")
    private String memberEmail;
    @Size(max = 100)
    private String memberPassword;
    @NotNull(message = "성별을 골라주세요")
    @Max(value = 2, message = "성별을 제대로 골라주세요")
    @Min(value = 0, message = "성별을 제대로 골라주세요")
    private Integer memberGender;
    private String memberProfileImg;

}
