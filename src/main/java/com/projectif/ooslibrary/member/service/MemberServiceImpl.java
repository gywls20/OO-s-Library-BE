package com.projectif.ooslibrary.member.service;

import com.projectif.ooslibrary.member.domain.Member;
import com.projectif.ooslibrary.member.dto.MemberJoinRequestDTO;
import com.projectif.ooslibrary.member.dto.MemberResponseDTO;
import com.projectif.ooslibrary.member.dto.MemberUpdateRequestDTO;
import com.projectif.ooslibrary.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원 가입
    @Transactional
    @Override
    public boolean memberJoin(MemberJoinRequestDTO memberJoinRequestDTO) {
        // DTO -> Entity
        Member newMember = Member.generalBuilder()
                .memberId(memberJoinRequestDTO.getMemberId())
                .memberName(memberJoinRequestDTO.getMemberName())
                .memberEmail(memberJoinRequestDTO.getMemberEmail())
                .memberPassword(passwordEncoder.encode(memberJoinRequestDTO.getMemberPassword())) // 비밀번호 Bcrypt로 인코딩
                .memberGender(memberJoinRequestDTO.getMemberGender())
                .buildGeneral();

        // repository 저장
        Member savedMember = memberRepository.save(newMember);

        if (savedMember.getMemberPk() != null) {
            return true;
        } else {
            return false;
        }

    }

    // 회원 수정 -> 예외 처리 아직 안함
    @Transactional
    @Override
    public boolean memberUpdate(MemberUpdateRequestDTO memberUpdateRequestDTO) {
        // Dirty Checking을 활용해 수정
        log.info("memberUpdateRequestDTO.getMemberPk() = {}", memberUpdateRequestDTO.getMemberPk());
        Member findMember = memberRepository.findById(memberUpdateRequestDTO.getMemberPk())
                .orElseThrow(() -> new RuntimeException("[MemberServiceImpl] - [memberUpdate] 해당하는 아이디를 가진 회원을 찾지 못함!!!"));
        // 비밀번호 Bcrypt로 인코딩
        memberUpdateRequestDTO.setMemberPassword(passwordEncoder.encode(memberUpdateRequestDTO.getMemberPassword()));
        findMember.memberUpdate(memberUpdateRequestDTO);
        return true;
    }

    // 회원 삭제
    @Transactional
    @Override
    public boolean memberDelete(Long memberPk, String memberPassword) {
        Member findMember = memberRepository.checkBeforeDelete(memberPk, memberPassword)
                .orElseThrow(() -> new RuntimeException("[MemberServiceImpl] - [memberDelete] 해당하는 아이디를 가진 회원을 찾지 못함!!!"));
        // 회원 is_deleted 플래그 업데이트 (회원 삭제 기능)
        findMember.memberDelete();
        return true;
    }

    // 회원 한 건 조회
    @Override
    public MemberResponseDTO getMember(Long memberPk) {

        Member findMember = memberRepository.findById(memberPk)
                .orElseThrow(() -> new RuntimeException("[MemberServiceImpl] - [getMember] 해당하는 아이디를 가진 회원을 찾지 못함!!!"));

        MemberResponseDTO dto = MemberResponseDTO.builder()
                .memberPk(findMember.getMemberPk())
                .memberId(findMember.getMemberId())
                .memberName(findMember.getMemberName())
                .memberEmail(findMember.getMemberEmail())
                .memberPassword(findMember.getMemberPassword())
                .memberGender(findMember.getMemberGender())
                .memberProfileImg(findMember.getMemberProfileImg())
                .build();

        return dto;
    }

    // 회원 한 건 조회 / 오버로딩
    @Override
    public MemberResponseDTO getMember(String memberId) {

        Member findMember = memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new RuntimeException("[MemberServiceImpl] - [getMember] 해당하는 아이디를 가진 회원을 찾지 못함!!!"));

        MemberResponseDTO dto = MemberResponseDTO.builder()
                .memberPk(findMember.getMemberPk())
                .memberId(findMember.getMemberId())
                .memberName(findMember.getMemberName())
                .memberEmail(findMember.getMemberEmail())
                .memberPassword(findMember.getMemberPassword())
                .memberGender(findMember.getMemberGender())
                .memberProfileImg(findMember.getMemberProfileImg())
                .build();

        return dto;
    }

    // 회원 전체 조회
    @Override
    public List<MemberResponseDTO> getMemberList() {
        List<Member> members = memberRepository.findAll();

        return members
                .stream()
                .map(Member::convertToDTO)
                .toList();
    }
}
