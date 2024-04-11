package com.projectif.ooslibrary.security.oauth;


import com.projectif.ooslibrary.member.domain.Member;
import com.projectif.ooslibrary.member.domain.Role;
import com.projectif.ooslibrary.member.exception.NoSuchMemberException;
import com.projectif.ooslibrary.member.repository.MemberRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        OAuth2User oAuth2User = (OAuth2User)authentication.getPrincipal();
        /*
         * 이 부분에 oAuth2User 정보를 회원 엔티티나 Dto에 넣기
         */
        log.info("Principal에서 꺼낸 OAuth2User = {}", oAuth2User);

        Map<String, Object> attributes = oAuth2User.getAttributes();
//        attributes.forEach((key, value) -> {
//            log.info("key = {} / value = {}", key, value);
//        });

        String auth = String.valueOf(oAuth2User.getAuthorities().toArray()[0]);
        auth = auth.substring(5);
//        log.info(auth);

        // 최초 로그인이라면 회원가입 처리를 한다.
        Member findMember = null;
        try {
            findMember = memberRepository.findByMemberId(oAuth2User.getName())
                    .orElseThrow(() -> new NoSuchMemberException("[OAuth2SuccessHandler] - [onAuthenticationSuccess] 해당하는 아이디를 가진 회원을 찾지 못함!!!"));
        } catch (NoSuchMemberException e) {
            log.info("[OAuth2SuccessHandler] : 찾는 회원이 없으므로 null 반환 / 최초 로그인이므로 회원 가입 수행");
        }

        // 네이버 로그인 API 성별은 M / F / U String으로 받아오므로 변환하기.
        String gender = (String) attributes.get("gender");
        Integer memberGender = 2;
        if (gender.equals("M")) {
            memberGender = 0;
        } else if (gender.equals("F")) {
            memberGender = 1;
        }

        // PK값 받아오기
        Long pk = null;

        if (findMember == null) { // 최초 로그인이므로 회원가입 처리하기
            Member member = Member.oauth2Builder()
                    .memberId(oAuth2User.getName())
                    .memberName((String) attributes.get("name"))
                    .memberEmail((String) attributes.get("email"))
                    .memberProfileImg((String) attributes.get("picture"))
                    .memberGender(memberGender)
                    .role(Role.valueOf(auth))
                    .buildOauth2();
            Member savedMember = memberRepository.save(member);
            findMember = savedMember;
        } else if (!findMember.getMemberName().equals(attributes.get("name")) || !findMember.getMemberProfileImg().equals(attributes.get("picture"))
                || !findMember.getRole().name().equals(auth)) {
            // 이 후 로그인이라도 혹시 naver 계정의 정보가 바뀔 수 있으니 바꾸는 로직을 추가할 지 고민해보기.
            // if문으로 지금 로그인한 oauth2user 정보와 db에 저장된 sns 회원 정보 중 변경될 가능성이 있는 필드만 비교 (프로필사진, 이름, 폰번호)
            // JPA Dirty Checking
            findMember.oauth2ChangeFields((String) attributes.get("name"), (String) attributes.get("picture"), Role.valueOf(auth));
        }
        pk = findMember.getMemberPk();

        String targetUrl = UriComponentsBuilder.fromUriString("/login/oauth2/success")
//                .queryParam("token", "token")
                .build().toUriString();

        /**
         * 세션에 로그인 정보 넣기
         * - 기본 oauth2 정보 + naver response 내 정보둘
         */
        HttpSession session = request.getSession(false);

        // pk값, 회원아이디, 프로필사진
        session.setAttribute("id", oAuth2User.getName()); // 웬만하면 세션에는 id같은 핵심 정보만 넣기.
        session.setAttribute("pk", pk);
        session.setAttribute("name", attributes.get("name"));
        session.setAttribute("profile", attributes.get("picture"));

        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

}
