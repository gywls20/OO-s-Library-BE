package com.projectif.ooslibrary.team.controller;

import com.projectif.ooslibrary.member.domain.Member;
import com.projectif.ooslibrary.member.exception.NoSuchMemberException;
import com.projectif.ooslibrary.member.repository.MemberRepository;
import com.projectif.ooslibrary.team.domain.Team;
import com.projectif.ooslibrary.team.repository.TeamRepository;
import com.projectif.ooslibrary.team.service.TeamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/team")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    // 회원의 나의 팀 검색 -> 팀의 팀원들 리턴
    @GetMapping("/{memberPk}")
    public String getTeam(@PathVariable("memberPk") Long memberPk, Model model) {

        Team team = teamService.getTeam(memberPk);
        List<Long> myLibraryPkList = team.getMembers().stream().map(member -> member.getMyLibrary().getMyLibraryPk()).toList();
        model.addAttribute("myLibraryPkList", myLibraryPkList);

        return "team/team";
    }
}
