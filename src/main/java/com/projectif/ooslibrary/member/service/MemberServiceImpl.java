package com.projectif.ooslibrary.member.service;

import com.projectif.ooslibrary.member.domain.Member;
import com.projectif.ooslibrary.member.dto.MemberCheckPasswordRequestDTO;
import com.projectif.ooslibrary.member.dto.MemberJoinRequestDTO;
import com.projectif.ooslibrary.member.dto.MemberResponseDTO;
import com.projectif.ooslibrary.member.dto.MemberUpdateRequestDTO;
import com.projectif.ooslibrary.member.exception.NoSuchMemberException;
import com.projectif.ooslibrary.member.repository.MemberRepository;
import com.projectif.ooslibrary.my_library.domain.MyLibrary;
import com.projectif.ooslibrary.my_library.repository.MyLibraryRepository;
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
    private final MyLibraryRepository myLibraryRepository;

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

        // 영속화한 member에 내서재 영속화 후, member에 참조
        MyLibrary myLibrary = MyLibrary.builder()
                .myLibraryName(savedMember.getMemberName() + "의 서재")
                .build();
        MyLibrary savedMyLibrary = myLibraryRepository.save(myLibrary);

        savedMember.addMyLibrary(savedMyLibrary);

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
        Member findMember = memberRepository.findByIdNotDeleted(memberUpdateRequestDTO.getMemberPk())
                .orElseThrow(() -> new NoSuchMemberException("[MemberServiceImpl] - [memberUpdate] 해당하는 아이디를 가진 회원을 찾지 못함!!!"));
        // 비밀번호 Bcrypt로 인코딩
        memberUpdateRequestDTO.setMemberPassword(passwordEncoder.encode(memberUpdateRequestDTO.getMemberPassword()));
        findMember.memberUpdate(memberUpdateRequestDTO);
        return true;
    }

    // 회원 삭제
    @Transactional
    @Override
    public boolean memberDelete(Long memberPk, String memberPassword) {
        log.info("memberPk = {}, memberPassword = {}", memberPk, memberPassword);
        Member findMember = memberRepository.findByIdNotDeleted(memberPk)
                .orElseThrow(() -> new NoSuchMemberException("[MemberServiceImpl] - [memberDelete] 해당하는 아이디를 가진 회원을 찾지 못함!!!"));
        boolean matches = passwordEncoder.matches(memberPassword, findMember.getMemberPassword());
        log.info("findMember = {}", findMember);
        log.info("findMember.getMemberPassword() = {}", findMember.getMemberPassword());
        log.info("matches boolean = {}", matches);
        // 회원 is_deleted 플래그 업데이트 (회원 삭제 기능)
        if (matches) {
            findMember.memberDelete();
            return true;
        } else {
            log.info("회원 삭제를 수행하지 못했습니다");
            return false;
        }
    }

    // 회원 마이페이지 비밀번호 체크
    @Override
    public boolean checkPassword(MemberCheckPasswordRequestDTO dto) {
        try {

            Member checkMember = memberRepository.findByIdNotDeleted(dto.getMemberPk())
                    .orElseThrow(() -> new NoSuchMemberException("[MemberServiceImpl] - [checkPassword] 해당하는 아이디를 가진 회원을 찾지 못함!!!"));
            String memberPassword = checkMember.getMemberPassword();
            boolean matches = passwordEncoder.matches(dto.getPassword(), memberPassword);
            return matches;
        } catch (RuntimeException e) {
            log.info("[MemberServiceImpl] - [checkPassword] : error 내용 : {}", e.getMessage());
            return false;
        }

    }

    // 회원 한 건 조회 -> isDeleted == 0 인 회원
    @Override
    public MemberResponseDTO getMember(Long memberPk) {

        Member findMember = memberRepository.findByIdNotDeleted(memberPk)
                .orElseThrow(() -> new NoSuchMemberException("[MemberServiceImpl] - [getMember] 해당하는 아이디를 가진 회원을 찾지 못함!!!"));

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

        Member findMember = memberRepository.findByMemberIdNotDeleted(memberId)
                .orElseThrow(() -> new NoSuchMemberException("[MemberServiceImpl] - [getMember] 해당하는 아이디를 가진 회원을 찾지 못함!!!"));

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

    @Override
    public List<MemberResponseDTO> getMemberListExceptDeleted() {
        List<Member> members = memberRepository.findAllNotDeleted();
        return members
                .stream()
                .map(Member::convertToDTO)
                .toList();
    }
}
