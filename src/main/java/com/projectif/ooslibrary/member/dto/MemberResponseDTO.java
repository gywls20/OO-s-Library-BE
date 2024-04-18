package com.projectif.ooslibrary.member.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberResponseDTO {
    /**
     * 회원 정보 응답 DTO -> 회원 수정 or 회원 정보 확인을 위해 정보창을 열었을 때 가져오는 회원 정보들
     * - PK값 : member_pk
     * - 아이디 : member_id
     * - 이름 : member_name
     * - 이메일 : member_email
     * - 비밀번호 : member_password
     * - 성별 : member_gender
     * - 프로필 사진 : member_profile
     *
     */
    private Long memberPk;
    private String memberId;
    private String memberName;
    private String memberEmail;
    private String memberPassword;
    private Integer memberGender;
    private String memberProfileImg;
    private String role;
    private Long myLibraryPk;

}
