package com.projectif.ooslibrary.member.controller;

import com.projectif.ooslibrary.member.dto.MemberResponseDTO;
import com.projectif.ooslibrary.member.exception.OAuth2LoginNoSessionValueException;
import com.projectif.ooslibrary.member.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@CrossOrigin(origins = {"http://localhost:3000", "http://192.168.0.8:3000", "https://libraryif.vercel.app:3000"}, maxAge = 3600)
@Slf4j
@RequiredArgsConstructor
@Controller
public class LoginController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String loginPage() {
        return "login/login";
    }

    // 로그인 성공
    @PostMapping("/login_success")
    public String loginSuccess(HttpServletRequest request) {

        // 현재 인증된 사용자 정보 가져오기
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // 사용자 이름 가져오기 > member의 memberId값 & 회원 정보 가져오기
        String memberId = ((UserDetails) auth.getPrincipal()).getUsername();
        MemberResponseDTO member = null;
        try {
            member = memberService.getMember(memberId);
            // 세션에 로그인 정보 넣기 - PK, Id, Profile 이미지 3개
            HttpSession session = request.getSession(false);
            session.setAttribute("id", memberId);
            session.setAttribute("pk", member.getMemberPk());
            session.setAttribute("name", member.getMemberName());
            session.setAttribute("profile", member.getMemberProfileImg());
            session.setAttribute("myLibraryPk", member.getMyLibraryPk());
            session.setAttribute("role", member.getRole());
            log.info("member.getMyLibraryPk() = {}", member.getMyLibraryPk());
            log.info("member.getRole() = {}", member.getRole());
        } catch (Exception e) {
            log.info("error ", e);
            throw new OAuth2LoginNoSessionValueException("해당 회원은 없거나 탈퇴 처리된 회원입니다");
        }

        return "redirect:/";
    }

    // 로그인 실패
    @PostMapping("/login_failure")
    public String loginFailure(HttpServletRequest request, Model model) {

        String errorMessage = (String) request.getAttribute("errorMessage");
        log.info("로그인 실패!! 에러 정보 = {}", errorMessage);

        model.addAttribute("errorMessage", errorMessage);

        return "login/login";
    }

//    @GetMapping("/logoutResult")
//    public String logoutResult(HttpServletResponse response) {
//        log.info("logout success url");
//
//        // CORS header 설정
//        response.setHeader("Access-Control-Allow-Credentials", "true");
//        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
//
//        return "로그아웃 성공!!";
//    }

    @GetMapping("/login/oauth2/fail")
    public String oauthLoginFail(HttpServletResponse response, HttpServletRequest request) {
        log.info("login failure url");

        // CORS header 설정
//        response.setHeader("Access-Control-Allow-Credentials", "true");
//        response.setHeader("Access-Control-Allow-Origin", "http://192.168.0.8:3000");

        request.getSession().invalidate();

        return "redirect:/login";
    }

    // 네이버 로그인 성공
    @GetMapping("/login/oauth2/success")
    public String oAuth2LoginSuccess(HttpServletRequest request) {

        // 세션에 로그인 정보 넣기 - PK, Id, Profile, name 4개
        HttpSession session = request.getSession(false);
        String id = (String) session.getAttribute("id");
        MemberResponseDTO member = memberService.getMember(id);
        if (id == null) {
            log.info("oAuth2 성공 핸들러 후, 세션이 안가져와 짐");
            throw new OAuth2LoginNoSessionValueException("oAuth2 성공 핸들러 후, 세션을 가져오지 못했습니다");
        }
        log.info("oauth2 성공 핸들러에서 세션을 성공적으로 받아옴");

        return "redirect:/";
    }

}
