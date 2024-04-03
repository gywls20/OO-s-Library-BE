package com.projectif.ooslibrary.admin.controller;

import com.projectif.ooslibrary.admin.domain.MemberVO;
import com.projectif.ooslibrary.admin.service.AdMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/adminPage")
@RequiredArgsConstructor
public class AdMemberController {

    private final AdMemberService adMamberService;
    @GetMapping("/memberlist")
    public String findMemberList(MemberVO vo, Model model) { // 회원
        // 목록 폼
        List<MemberVO> memberlist = adMamberService.findAllMember();

        model.addAttribute("memberlist", memberlist);

        return "adminPage/memberlist";
    }
}
