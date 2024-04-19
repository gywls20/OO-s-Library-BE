package com.projectif.ooslibrary.admin.controller;

import com.projectif.ooslibrary.admin.domain.MemberVO;
import com.projectif.ooslibrary.admin.service.AdMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/adminPage")
@Slf4j
@RequiredArgsConstructor
public class AdMemberController {

    private final AdMemberService adMemberService;




    @GetMapping("/memberList")
    public String findMemberList(Model model) { // 회원
        // 목록 폼
        List<MemberVO> memberlist = adMemberService.findAllMember();


        log.info("회원목록요청");	//로그

        model.addAttribute("title", "회원목록조회");
        model.addAttribute("memberList", memberlist);

        return "pages/admin/memberList";
    }
}
