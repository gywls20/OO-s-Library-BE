package com.projectif.ooslibrary.member.controller;

import com.projectif.ooslibrary.member.dto.MemberJoinRequestDTO;
import com.projectif.ooslibrary.member.dto.MemberResponseDTO;
import com.projectif.ooslibrary.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 회원 정보 한 건 조회
    @GetMapping("/{id}")
    public MemberResponseDTO getMember(@PathVariable("id") Long id) {
        return memberService.getMember(id);
    }

    // 회원 전체 리스트 조회
    @GetMapping("")
    public List<MemberResponseDTO> getMemberList() {
        return memberService.getMemberList();
    }


    // 회원 가입
    @PostMapping("")
    public boolean memberJoin(@ModelAttribute("member")MemberJoinRequestDTO member) {
        return memberService.memberJoin(member);
    }

    // 회원 수정

    // 회원 삭제

}
