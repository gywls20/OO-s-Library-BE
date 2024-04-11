//package com.projectif.ooslibrary.member.service;
//
//import com.projectif.ooslibrary.member.domain.Member;
//import com.projectif.ooslibrary.member.dto.MemberJoinRequestDTO;
//import com.projectif.ooslibrary.member.dto.MemberResponseDTO;
//import com.projectif.ooslibrary.member.dto.MemberUpdateRequestDTO;
//import com.projectif.ooslibrary.member.repository.MemberRepository;
//import jakarta.persistence.EntityManager;
//import lombok.extern.slf4j.Slf4j;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.NoSuchElementException;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//
//@Transactional
//@SpringBootTest
//@Slf4j
//class MemberServiceImplTest {
//
//    @Autowired
//    private MemberService memberService;
//    @Autowired
//    private MemberRepository memberRepository;
//    @Autowired
//    private EntityManager em;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @BeforeEach
//    void init() {
//        Member member = new Member("test", "test Lee", "test@naver.com", passwordEncoder.encode("1234"), 0);
//        memberRepository.save(member);
//        Member member2 = new Member("test2", "test Lim", "test2@naver.com", passwordEncoder.encode("1234"), 0);
//        memberRepository.save(member2);
//        Member member3 = new Member("test3", "test Kim", "test3@naver.com", passwordEncoder.encode("1234"), 1);
//        memberRepository.save(member3);
//    }
//
//    @AfterEach
//    void clear() {
//        memberRepository.deleteAll();
//    }
//
//    @Test
//    void memberJoin() {
//        // give
//        boolean joined = memberService.memberJoin(
//                MemberJoinRequestDTO
//                        .builder()
//                        .memberId("test4")
//                        .memberName("test Lee4")
//                        .memberEmail("test4@naver.com")
//                        .memberPassword("1234")
//                        .memberGender(0)
//                        .build()
//        );
//
//        log.info("[MemberJoin Test] - joined = {}", joined);
//        memberRepository.flush();
//        em.clear();
//
//        // when
//        Member findMember = memberRepository.findByMemberId("test4").get();
//        log.info("findMember = {}", findMember);
//
//        // then
//        assertThat(findMember.getMemberEmail()).isEqualTo("test4@naver.com");
//    }
//
////    @Test
////    void memberUpdate() {
////        MemberUpdateRequestDTO dto = MemberUpdateRequestDTO.builder()
////                .memberPk(1L)
////                .memberName("updatedName")
////                .memberGender(1)
////                .memberEmail("updated@naver.com")
////                .memberPassword("updated")
////                .memberProfileImg("zzz")
////                .build();
////
////        List<MemberResponseDTO> memberList = memberService.getMemberList();
////        for (MemberResponseDTO memberResponseDTO : memberList) {
////            log.info("[Iter member List] {}", memberResponseDTO);
////        }
////
////        memberService.memberUpdate(dto);
////
////
////        memberRepository.flush();
////        em.clear();
////
////        Member findMember = memberRepository.findById(1L).orElseThrow(() -> new NoSuchElementException("No Member"));
////
////        assertThat(findMember.getMemberName()).isEqualTo("updatedName");
////        assertThat(findMember.getMemberGender()).isEqualTo(1);
////        assertThat(findMember.getMemberEmail()).isEqualTo("updated@naver.com");
////        assertThat(findMember.getMemberProfileImg()).isEqualTo("zzz");
////
////    }
//
//    @Test
//    void memberDelete() {
//        Member member1 = memberRepository.findByMemberId("test").orElseThrow(() -> new NoSuchElementException("No Member"));
//        Member member2 = memberRepository.findByMemberId("test2").orElseThrow(() -> new NoSuchElementException("No Member"));
//        Member member3 = memberRepository.findByMemberId("test3").orElseThrow(() -> new NoSuchElementException("No Member"));
//
//        memberService.memberDelete(member1.getMemberPk(), "1234");
//        memberService.memberDelete(member2.getMemberPk(), "1234");
//        memberService.memberDelete(member3.getMemberPk(), "1234");
//
//        em.flush();
//        em.clear();
//
//        Member findMember1 = memberRepository.findByMemberId("test").orElseThrow(() -> new NoSuchElementException("No Member"));
//        Member findMember2 = memberRepository.findByMemberId("test2").orElseThrow(() -> new NoSuchElementException("No Member"));
//        Member findMember3 = memberRepository.findByMemberId("test3").orElseThrow(() -> new NoSuchElementException("No Member"));
//
//        assertThat(findMember1.getIsDeleted()).isEqualTo(1);
//        assertThat(findMember2.getIsDeleted()).isEqualTo(1);
//        assertThat(findMember3.getIsDeleted()).isEqualTo(1);
//    }
//
////    @Test
////    void getMember() {
////        MemberResponseDTO member1DTO = memberService.getMember(1L);
////        MemberResponseDTO member2DTO = memberService.getMember(2L);
////        MemberResponseDTO member3DTO = memberService.getMember(3L);
////        Member member1 = memberRepository.findByMemberId(member1DTO.getMemberId()).orElseThrow(() -> new NoSuchElementException("No Member"));
////        Member member2 = memberRepository.findByMemberId(member2DTO.getMemberId()).orElseThrow(() -> new NoSuchElementException("No Member"));
////        Member member3 = memberRepository.findByMemberId(member3DTO.getMemberId()).orElseThrow(() -> new NoSuchElementException("No Member"));
////
////
////        assertThat(member1.getMemberName()).isEqualTo("test Lee");
////        assertThat(member2.getMemberName()).isEqualTo("test Lim");
////        assertThat(member3.getMemberName()).isEqualTo("test Kim");
////
////        assertThat(member1.getMemberEmail()).isEqualTo("test@naver.com");
////        assertThat(member2.getMemberEmail()).isEqualTo("test2@naver.com");
////        assertThat(member3.getMemberEmail()).isEqualTo("test3@naver.com");
////    }
//
//    @Test
//    void getMemberList() {
//
//        List<MemberResponseDTO> memberList = memberService.getMemberList();
//
//        for (MemberResponseDTO dto : memberList) {
//            log.info("member = {}", dto);
//        }
//    }
//}