package com.projectif.ooslibrary.member.service;

import com.projectif.ooslibrary.member.dto.MemberCheckPasswordRequestDTO;
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
    // 회원 한 건 조회 by memberId
    MemberResponseDTO getMember(String memberId);
    // 회원 전체 조회 (삭제된 것 포함)
    List<MemberResponseDTO> getMemberList();

    // 회원 전체 조회 (삭제 플래그가 없는 것들)
    List<MemberResponseDTO> getMemberListExceptDeleted();
    // 회원 마이페이지 본인 확인용 비밀번호 체크
    boolean checkPassword(MemberCheckPasswordRequestDTO dto);
}
