package com.projectif.ooslibrary.team.service;

import com.projectif.ooslibrary.member.domain.Member;
import com.projectif.ooslibrary.member.exception.NoSuchMemberException;
import com.projectif.ooslibrary.member.repository.MemberRepository;
import com.projectif.ooslibrary.team.domain.Team;
import com.projectif.ooslibrary.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final MemberRepository memberRepository;

    public Team getTeam(Long memberPk) {
        Member member = memberRepository.findById(memberPk)
                .orElseThrow(() -> new NoSuchMemberException("회원이 존재하지 않습니다"));

        return member.getTeam();
    }
}
