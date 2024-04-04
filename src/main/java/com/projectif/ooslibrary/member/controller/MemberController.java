package com.projectif.ooslibrary.member.controller;

import com.projectif.ooslibrary.member.dto.MemberCheckPasswordRequestDTO;
import com.projectif.ooslibrary.member.dto.MemberJoinRequestDTO;
import com.projectif.ooslibrary.member.dto.MemberResponseDTO;
import com.projectif.ooslibrary.member.dto.MemberUpdateRequestDTO;
import com.projectif.ooslibrary.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 회원 정보 한 건 조회 -> 나중에 삭제 처리된 회원은 안나오도록 하기.
    @GetMapping("/{id}")
    public MemberResponseDTO getMember(@PathVariable("id") Long id) {
        return memberService.getMember(id);
    }

    // 회원 마이페이지 접근 시 -> 비밀 번호 체크 기능
    @PostMapping("/checkPassword")
    public boolean checkPassword(@RequestBody @Validated MemberCheckPasswordRequestDTO dto) {
        log.info("[MemberController] - [checkPassword] : pk = {}, password = {}", dto.getMemberPk(), dto.getPassword());
        return memberService.checkPassword(dto);
    }

    // 회원 전체 리스트 조회 - 어드민 전용
//    @GetMapping("")
//    public List<MemberResponseDTO> getMemberList() {
//        return memberService.getMemberList();
//    }

    // 회원 전체 리스트 조회 - 삭제 안된 회원들
    @GetMapping("")
    public List<MemberResponseDTO> getMemberListNotDeleted() {
        return memberService.getMemberListExceptDeleted();
    }

    // 회원 가입
    @PostMapping("")
    public boolean memberJoin(@RequestBody @Validated MemberJoinRequestDTO member) {
        return memberService.memberJoin(member);
    }

    // 회원 수정
    @PutMapping("/{id}")
    public boolean memberUpdate(@PathVariable("id") Long id, @RequestBody @Validated MemberUpdateRequestDTO dto) {
        dto.setMemberPk(id);
        return memberService.memberUpdate(dto);
    }

    // 회원 삭제
    @DeleteMapping("/{id}")
    public boolean memberDelete(@PathVariable("id") Long id, @RequestBody Map<String, String> passwordMap) {
        String memberPassword = passwordMap.get("memberPassword");
        log.info("memberPassword = {}", memberPassword);
        return memberService.memberDelete(id, memberPassword);
    }

}
