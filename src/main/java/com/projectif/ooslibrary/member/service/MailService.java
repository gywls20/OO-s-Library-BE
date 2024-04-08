package com.projectif.ooslibrary.member.service;

import com.projectif.ooslibrary.member.domain.Mail;
import com.projectif.ooslibrary.member.domain.Member;
import com.projectif.ooslibrary.member.exception.MailNotVerifiedException;
import com.projectif.ooslibrary.member.exception.NoSuchMemberException;
import com.projectif.ooslibrary.member.exception.NoSuchVerifyCodeException;
import com.projectif.ooslibrary.member.repository.MailRepository;
import com.projectif.ooslibrary.member.repository.MemberRepository;
import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailRepository mailRepository;

    @Value("${mail-id}")
    private String NAVER_LOGIN_MAIL_ID;


    private MimeMessage createMessage(String code, String email) throws Exception {
        MimeMessage message = javaMailSender.createMimeMessage();
        message.addRecipients(Message.RecipientType.TO, email);
        message.setSubject("OO의 서재 인증 번호입니다.");
        message.setText("안녕하세요 OO의 서재 입니다. \n" +
                "이메일 인증코드: " + code);

        message.setFrom(new InternetAddress(NAVER_LOGIN_MAIL_ID + "@naver.com", "OO의 서재 관리센터", StandardCharsets.UTF_8.name()));
        return message;
    }

    public void sendMail(String code, String email) throws Exception{
        try{
            MimeMessage mimeMessage = createMessage(code, email);
            javaMailSender.send(mimeMessage);
        }catch (MailException mailException){
            log.info("error = {}", mailException.toString());
            throw new IllegalAccessException();
        }
    }

    // 인증 코드 보내기
    @Transactional
    public String sendCertificationMail(String email, String name)  throws RuntimeException {
        Member findMember = memberRepository.findByMemberEmailAndMemberName(email, name)
                .orElseThrow(() -> new NoSuchMemberException("유효하지 않은 회원의 Email 입니다!!!"));
        if(!findMember.getMemberEmail().equals(email)){
            throw new RuntimeException("[MailService] - [sendCertificationMail] NOT FOUND EMAIL");
        }
        try{
            String  code = UUID.randomUUID().toString().substring(0, 8);
            sendMail(code, email);

            mailRepository.save(new Mail(code, email));
            return code;
        }catch (Exception exception){
            log.info("error = {}", exception.toString());
            throw new RuntimeException("Mail이 발송되지 못했습니다");
        }
    }

    /**
     * 받은 이름 - 이메일로 두 정보가 일치하는 회원이 있는 지 확인하는 로직 필요
     */

    /**
     * 받은 인증 메일 코드와 일치하는 이메일 찾기 -> 이메일 인증하기
     * @param code
     * @return 인증한 Mail Entity의 이메일 값
     */
    @Transactional
    public String verifyEmailByCode(String code) {
        Mail mail = mailRepository.findByCode(code).orElseThrow(() -> new NoSuchVerifyCodeException("인증 코드가 맞지 않습니다!!!"));
        int isVerified = mailRepository.verifiedEmail(mail.getCode(), mail.getEmail());
        if (isVerified < 1) {
            throw new MailNotVerifiedException("[MailService] [findEmailByVerifyCode] : 해당 이메일은 인증되지 못했습니다.");
        }
        return mail.getEmail();
    }

    // 인증된 회원의 임시 비밀번호 보내기
    @Transactional
    public String sendNewPasswordMail(String email, String name)  throws RuntimeException {
        Member findMember = memberRepository.findByMemberEmailAndMemberName(email, name)
                .orElseThrow(() -> new NoSuchMemberException("유효하지 않은 회원의 Email 입니다!!!"));
        if(!findMember.getMemberEmail().equals(email)){
            throw new RuntimeException("[MailService] - [sendCertificationMail] NOT FOUND EMAIL");
        }
        try{
            String  code = UUID.randomUUID().toString().substring(0, 10);
            sendMail(code, email);

            mailRepository.save(new Mail(code, email));
            Member member = memberRepository.findByMemberEmailAndMemberName(email, name)
                    .orElseThrow(() -> new NoSuchMemberException("[MemberServiceImpl] - [getMember] 해당하는 아이디를 가진 회원을 찾지 못함!!!"));
            // 임시 비밀번호 저장
            member.changePassword(passwordEncoder.encode(code));

            return code;
        }catch (Exception exception){
            log.info("error = {}", exception.toString());
            throw new RuntimeException("Mail이 발송되지 못했습니다");
        }
    }
}
