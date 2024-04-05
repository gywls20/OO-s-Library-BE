package com.projectif.ooslibrary.member.repository;

import com.projectif.ooslibrary.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByMemberId(String memberId);

    /**
     * 승훈 돼지
     * @param memberId
     * @param memberPassword
     * @return 승훈 몸무게
     */
    Optional<Member> findByMemberIdAndMemberPassword(String memberId, String memberPassword);

    @Query("select m from Member m where m.memberPk = :memberPk and m.memberPassword = :memberPassword")
    Optional<Member> checkPkAndPassword(@Param("memberPk") Long memberPk, @Param("memberPassword") String memberPassword);

    @Query("select m from Member m where m.isDeleted != 1")
    List<Member> findAllNotDeleted();

    /**
     * 회원 이메일로 회원 정보 찾기
     * @param memberEmail
     * @return
     */
    Optional<Member> findByMemberEmailAndMemberName(String memberEmail, String memberName);
}
