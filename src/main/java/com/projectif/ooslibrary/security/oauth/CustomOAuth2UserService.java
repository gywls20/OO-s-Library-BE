package com.projectif.ooslibrary.security.oauth;

import com.projectif.ooslibrary.member.domain.Member;
import com.projectif.ooslibrary.member.domain.Role;
import com.projectif.ooslibrary.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        //  1번
        OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService = new DefaultOAuth2UserService();

        //	2번
        OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);

        //	3번
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        log.info("registrationId = {}", registrationId);
        log.info("userNameAttributeName = {}", userNameAttributeName);

        // 4번
        OAuth2Attribute oAuth2Attribute =
                OAuth2Attribute.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
        // email로 memberRepository에 있는지 확인하고 있다면 권한 설정.
        Member findMember = null;
        try {
            findMember = memberRepository.findByMemberId((String) oAuth2Attribute.getAttributes().get("email"))
                    .orElseThrow(() -> new RuntimeException("[CustomOAuth2UserService] - [loadUser] 해당하는 아이디를 가진 회원을 찾지 못함!!!"));
        } catch (RuntimeException e) {
            log.info("[CustomOAuth2UserService] : 찾는 회원이 없으므로 findMember = NULL 반환");
        }
        String role = "USER";
        if (findMember != null) {
            log.info("[OAuth2UserService] memberRepository 이미 회원가입된 회원의 권한이 있는 지 확인 findMember의 권한 = {}", findMember.getRole());
            role = findMember.getRole().name();
        }

        var memberAttribute = oAuth2Attribute.convertToMap();

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("ROLE_" + role)),
                memberAttribute,
                "email"
        );
    }


}
