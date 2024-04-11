package com.projectif.ooslibrary.member.repository;

import com.projectif.ooslibrary.member.domain.Mail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MailRepository extends JpaRepository<Mail, Long> {

    /**
     * 아직 검증되지 않은 인증 코드를 받은 이메일 인증 정보 가져오기
     * @param code
     * @return
     */
    @Query("select m from Mail m where m.code = :code and m.isVerified = 0")
    Optional<Mail> findByCode(@Param("code") String code);

    /**
     * 인증 완료된 이메일과 코드 정보를 가진 Mail 엔티티를 인증완료 상태로 변경한다
     *
     * @param code
     * @param email
     */
    @Query("update Mail m set m.isVerified = 1 where m.email = :email and m.code = :code")
    @Modifying
    int verifiedEmail(@Param("code") String code, @Param("email") String email);

}
