package com.projectif.ooslibrary.member.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberJoinRequestDTO {
    /**
     * 회원 가입 요청 DTO
     */
    private String memberId;
    private String memberName;
    private String memberEmail;
    private String memberPassword;
    private Integer memberGender;

}
