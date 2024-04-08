package com.projectif.ooslibrary.exceptions;

import com.projectif.ooslibrary.member.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE) // 예외 핸들러 어드바이스 중 1순위 -> 어드바이스가 여러 개일 경우, order 설정해야 함.
public class MemberExceptionController {
    /**
     * 어플리케이션에서 발생한 모든 예외를 모아서 처리
     */

    /**
     * 회원 기능 중 관련되어 발생 된 커스텀 예외  (Member / Mail)
     * - MemberNotFoundException : pk값으로 회원을 못찾을 시 발생한 예외
     * -
     *
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

        return new ResponseEntity<>(ex.toString(), headers, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({OAuth2LoginNoSessionValueException.class})
    public ResponseEntity<String> oAuth2LoginNoSessionValueException(OAuth2LoginNoSessionValueException ex) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Error", "OAuth2LoginNoSessionValueException");

        log.info("OAuth2LoginNoSessionValueException 예외 발생 [MemberControllerAdvice] : {}", ex.getMessage());

        return new ResponseEntity<>(ex.toString(), headers, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({NoSuchVerifyCodeException.class})
    public ResponseEntity<String> noSuchVerifyCodeException(NoSuchVerifyCodeException ex) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Error", "NoSuchVerifyCodeException");

        log.info("NoSuchVerifyCodeException 예외 발생 [MemberControllerAdvice] : {}", ex.getMessage());

        return new ResponseEntity<>(ex.toString(), headers, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({MailNotVerifiedException.class})
    public ResponseEntity<String> mailNotVerifiedException(MailNotVerifiedException ex) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Error", "MailNotVerifiedException");

        log.info("MailNotVerifiedException 예외 발생 [MemberControllerAdvice] : {}", ex.getMessage());

        return new ResponseEntity<>(ex.toString(), headers, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MailNotSendException.class})
    public ResponseEntity<String> mailNotSendException(MailNotSendException ex) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Error", "MailNotSendException");

        log.info("MailNotSendException 예외 발생 [MemberControllerAdvice] : {}", ex.getMessage());

        return new ResponseEntity<>(ex.toString(), headers, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<String> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Error", "MethodArgumentNotValidException");

        log.info("MethodArgumentNotValidException 예외 발생 [MemberControllerAdvice] : {}", Objects.requireNonNull(ex.getFieldError()).getDefaultMessage());

        return new ResponseEntity<>(Objects.requireNonNull(ex.getFieldError()).getDefaultMessage(), headers, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<String> httpMessageNotReadableException(HttpMessageNotReadableException ex) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Error", "HttpMessageNotReadableException");

        log.info("HttpMessageNotReadableException 예외 발생 [MemberControllerAdvice] : {}", ex.toString());

        return new ResponseEntity<>("잘못된 데이터 접근입니다!!!", headers, HttpStatus.BAD_REQUEST);
    }

    /**
     * 데이터 무결성 위반 예외 핸들링
     * @param ex
     * @return
     */
    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<String> dataIntegrityViolationException(DataIntegrityViolationException ex) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Error", "DataIntegrityViolationException");

        log.info("DataIntegrityViolationException 예외 발생 [MemberControllerAdvice] : {}", ex.toString());

        return new ResponseEntity<>("ID나 Email은 중복될 수 없습니다", headers, HttpStatus.BAD_REQUEST);
    }

}
