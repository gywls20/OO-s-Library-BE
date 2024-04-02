package com.projectif.ooslibrary.member.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
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
    private String memberId;
    private String memberName;
    private String memberEmail;
    private String memberPassword;
    private Integer memberGender;
    private String memberProfileImg;

}
