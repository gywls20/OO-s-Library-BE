package com.projectif.ooslibrary.team.controller;

import com.projectif.ooslibrary.member.domain.Member;
import com.projectif.ooslibrary.member.dto.MemberResponseDTO;
import com.projectif.ooslibrary.member.exception.NoSuchMemberException;
import com.projectif.ooslibrary.member.repository.MemberRepository;
import com.projectif.ooslibrary.team.domain.Team;
import com.projectif.ooslibrary.team.repository.TeamRepository;
import com.projectif.ooslibrary.team.service.TeamService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/team")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;
    private final HttpSession session;

    // 회원의 나의 팀 검색 -> 팀의 팀원들 리턴
    @GetMapping("/{memberPk}")
    public String getTeam(@PathVariable("memberPk") Long memberPk, Model model) {

        Team team = teamService.getTeam(memberPk);

        if (team == null) {
            return "redirect:/team/add";
        } else {

            List<MemberResponseDTO> members = team.getMembers().stream().map(member ->
                    MemberResponseDTO.builder()
                            .memberPk(member.getMemberPk())
                            .memberName(member.getMemberName())
                            .myLibraryPk(member.getMyLibrary().getMyLibraryPk())
                            .memberProfileImg(member.getMemberProfileImg())
                            .build()
            ).toList();

            model.addAttribute("members", members);
            model.addAttribute("teamPk", team.getTeamPk());

            return "team/team";
        }
    }

    // 팀 추가 페이지
    @GetMapping("/add")
    public String addTeamForm(Model model) {

        return "team/add";
    }

    @PostMapping("/add")
    public String addTeam(Model model, @RequestParam("teamName") String teamName) {

        Long memberPk = (Long) session.getAttribute("pk");
        teamService.addTeam(teamName, memberPk);

        return "redirect:/";
    }

    // 팀원 추가 로직
    @PostMapping("/addMember/{teamPk}")
    @ResponseBody
    public boolean addTeamMember(Model model,
                                @RequestBody Map<String, String> memberIdMap,
                                @PathVariable("teamPk") Long teamPk) {
        String memberId = memberIdMap.get("memberId");
        teamService.addTeamMember(teamPk, memberId);

        return true;
    }

    // 팀원 삭제 로직
    @PostMapping("/deleteMember/{teamPk}")
    @ResponseBody
    public boolean deleteTeamMember(Model model,
                                 @RequestBody Map<String, String> memberIdMap,
                                 @PathVariable("teamPk") Long teamPk) {
        String memberId = memberIdMap.get("memberId");
        teamService.deleteTeamMember(teamPk, memberId);

        return true;
    }
}
