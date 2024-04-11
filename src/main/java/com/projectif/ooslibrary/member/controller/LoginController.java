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
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:3000", "http://192.168.0.8:3000", "https://libraryif.vercel.app:3000"}, maxAge = 3600)
@Slf4j
@RequiredArgsConstructor
@Controller
public class LoginController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String loginPage() {
        return "test/login";
    }

    // 로그인 성공
    @PostMapping("/login_success")
    @ResponseBody
    public Map<String, String> loginSuccess(HttpServletRequest request) {

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
        } catch (Exception e) {
            throw new OAuth2LoginNoSessionValueException("해당 회원은 없거나 탈퇴 처리된 회원입니다");
        }


        /**
         *  - 스프링 컨트롤러로 오브젝트 or String을 보내도 헤더에 JSESSIONID 쿠키를 생성해서 보내줌.
         *  - 세션 / 쿠키 방식을 위해 굳이 쿠키를 생성하지 않고 스프링 시큐리티가 생성해 보내주는 세션아이디 JSESSIONID 사용.
         */

        HashMap<String, String> map = new HashMap<>();
        map.put("pk", String.valueOf(member.getMemberPk()));
        map.put("id", memberId);
        map.put("name", member.getMemberName());
        map.put("profile", member.getMemberProfileImg());

        return map;
    }

    // 로그인 실패
    @PostMapping("/login_failure")
    @ResponseBody
    public String loginFailure(HttpServletRequest request) {

        String errorMessage = (String) request.getAttribute("errorMessage");
        log.info("로그인 실패!! 에러 정보 = {}", errorMessage);

        return errorMessage;
    }

    @GetMapping("/logoutResult")
    @ResponseBody
    public String logoutResult(HttpServletResponse response) {
        log.info("logout success url");

        // CORS header 설정
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");

        return "로그아웃 성공!!";
    }

    @GetMapping("/login/oauth2/fail")
    @ResponseBody
    public String oauthLoginFail(HttpServletResponse response, HttpServletRequest request) {
        log.info("login failure url");

        // CORS header 설정
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Origin", "http://192.168.0.8:3000");

        request.getSession().invalidate();

        return "멍충멍충";
    }

    // 네이버 로그인 성공
    @GetMapping("/login/oauth2/success")
    @ResponseBody
    public Map<String, String> oAuth2LoginSuccess(HttpServletRequest request) {

        // 세션에 로그인 정보 넣기 - PK, Id, Profile, name 4개
        HttpSession session = request.getSession(false);
        String id = (String) session.getAttribute("id");
        MemberResponseDTO member = memberService.getMember(id);
        if (id == null) {
            log.info("oAuth2 성공 핸들러 후, 세션이 안가져와 짐");
            throw new OAuth2LoginNoSessionValueException("oAuth2 성공 핸들러 후, 세션이 안가져와 짐");
        }
        log.info("oauth2 성공 핸들러에서 세션을 성공적으로 받아옴");

        /** TODO : 리액트에 session / cookie를 어떻게 던져줄 지 고민.
         *  - 스프링 컨트롤러로 오브젝트 or String을 보내도 헤더에 JSESSIONID 쿠키를 생성해서 보내줌
         *  - 세션 / 쿠키 방식을 위해 굳이 쿠키를 생성하지 않고 스프링 시큐리티가 생성해 보내주는 세션아이디를 쓰면 될 것 같음.
         */

        HashMap<String, String> map = new HashMap<>();
        map.put("pk", String.valueOf(member.getMemberPk()));
        map.put("id", id);
        map.put("name", member.getMemberName());
        map.put("profile", member.getMemberProfileImg());

        return map;
    }

}
