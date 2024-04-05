package com.projectif.ooslibrary.member.controller;

import com.projectif.ooslibrary.member.dto.EmailVerifyRequestDTO;
import com.projectif.ooslibrary.member.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

    // 이메일 인증 발송
    @PostMapping("")
    public String sendEmail(@RequestBody EmailVerifyRequestDTO dto) {

        String verifyCode = mailService.sendCertificationMail(dto.getEmail(), dto.getName());
        log.info("인증 코드 = {}", verifyCode);

        return "인증코드 발송 완료!!";
    }
}
