package com.projectif.ooslibrary.member.repository;

import com.projectif.ooslibrary.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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
    
}
