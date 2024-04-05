package com.projectif.ooslibrary.exceptions;

import com.projectif.ooslibrary.member.exception.NoSuchMemberException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE) // 예외 핸들러 어드바이스 중 1순위 -> 어드바이스가 여러 개일 경우, order 설정해야 함.
public class MemberExceptionController {
    /**
     * 어플리케이션에서 발생한 모든 예외를 모아서 처리
     */

    /**
     * 회원 기능 중 관련되어 발생 된 커스텀 예외 핸들링
     * - MemberNotFoundException : pk값으로 회원을 못찾을 시 발생한 예외
     * -
     * @param ex
     * @return
     */
    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<String> memberException(RuntimeException ex) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Error", "RuntimeException");

        log.info("RuntimeException Exception 발생 [MemberControllerAdvice] : {}", ex.getMessage());

        return new ResponseEntity<>(ex.toString(), headers, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NoSuchMemberException.class})
    public ResponseEntity<String> noSuchMemberException(NoSuchMemberException ex) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Error", "NoSuchMemberException");

        log.info("NoSuchMemberException 예외 발생 [MemberControllerAdvice] : {}", ex.getMessage());

        return new ResponseEntity<>(ex.toString(), headers, HttpStatus.BAD_REQUEST);
    }

}
