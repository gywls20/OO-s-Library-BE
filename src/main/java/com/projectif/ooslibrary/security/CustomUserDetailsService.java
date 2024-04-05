package com.projectif.ooslibrary.security;

import com.projectif.ooslibrary.member.domain.Member;
import com.projectif.ooslibrary.member.exception.NoSuchMemberException;
import com.projectif.ooslibrary.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        Member findMember = memberRepository.findByMemberId(userId)
                .orElseThrow(() -> new NoSuchMemberException("[CustomUserDetailsService] - [loadUserByUsername] 해당하는 아이디를 가진 회원을 찾지 못함!!!"));

        return User.builder()
                .username(findMember.getMemberId())
                .password(findMember.getMemberPassword())
                .disabled(!findMember.isEnabled()) // 사용자에 의해 확정되지 않았다면 비활성으로 분류
                .accountExpired(!findMember.isAccountNonExpired()) // 만료 여부
                .accountLocked(!findMember.isAccountNonLocked()) // 잠금 여부
                .roles(findMember.getRole().name())
                .build();
    }
}
