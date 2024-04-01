package com.projectif.ooslibrary.member.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage() {
        return "test/login";
    }

    @PostMapping("/login_success")
    public String login_success(HttpServletRequest request) {

        // 현재 인증된 사용자 정보 가져오기
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // 사용자 이름 가져오기 > member의 memberId값
        String id = ((UserDetails) auth.getPrincipal()).getUsername();


        // 세션에 사용자 이름 저장
        HttpSession session = request.getSession();
        session.setAttribute("id", id);

        return "redirect:/";
    }
}
