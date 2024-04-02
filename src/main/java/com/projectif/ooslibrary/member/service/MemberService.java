package com.projectif.ooslibrary.member.service;

import com.projectif.ooslibrary.member.dto.MemberJoinRequestDTO;
import com.projectif.ooslibrary.member.dto.MemberUpdateRequestDTO;
import com.projectif.ooslibrary.member.dto.MemberResponseDTO;

import java.util.List;

public interface MemberService {

    // 회원 가입
    boolean memberJoin(MemberJoinRequestDTO memberJoinRequestDTO);
    // 회원 수정
    boolean memberUpdate(MemberUpdateRequestDTO memberUpdateRequestDTO);
    // 회원 삭제 -> isDeleted 필드 1 로 변경
    boolean memberDelete(Long memberPk, String memberPassword);
    // 회원 한 건 조회
    MemberResponseDTO getMember(Long memberPk);
    // 회원 전체 조회
    List<MemberResponseDTO> getMemberList();
}
