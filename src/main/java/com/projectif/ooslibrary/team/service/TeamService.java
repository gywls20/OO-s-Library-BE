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

    public Team getTeamByTeamPk(Long teamPk) {
        return teamRepository.findById(teamPk).orElseThrow(() -> new RuntimeException("팀 서재가 존재하지 않습니다"));
    }

    @Transactional
    public void addTeam(String teamName, Long memberPk) {
        Member member = memberRepository.findById(memberPk)
                .orElseThrow(() -> new NoSuchMemberException("회원이 존재하지 않습니다"));
        Team team = new Team(teamName);
        member.addTeam(team);
        teamRepository.save(team);
    }

    // 팀원 추가 로직
    @Transactional
    public void addTeamMember(Long teamPk, String memberId) {
        Team team = getTeamByTeamPk(teamPk);
        Member member = memberRepository.findByMemberId(memberId).orElseThrow(() -> new NoSuchMemberException("존재하지 않는 회원입니다"));
        if (member.getTeam() != null) {
            throw new RuntimeException("이미 팀에 합류한 인원입니다");
        }
        team.addMember(member);
        member.addTeam(team);
    }

    // 팀원 삭제 로직
    @Transactional
    public void deleteTeamMember(Long teamPk, String memberId) {
        Team team = getTeamByTeamPk(teamPk);
        Member member = memberRepository.findByMemberId(memberId).orElseThrow(() -> new NoSuchMemberException("존재하지 않는 회원입니다"));
        if (member.getTeam() == null) {
            throw new RuntimeException("이미 팀에 합류하지 않은 인원입니다");
        }
        // 연관관계 끊기
        team.deleteMember(member);
        member.deleteTeam();
    }
}
