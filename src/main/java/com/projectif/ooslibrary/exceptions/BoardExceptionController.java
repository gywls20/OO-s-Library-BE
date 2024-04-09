package com.projectif.ooslibrary.exceptions;

import com.projectif.ooslibrary.board.exception.NoSuchBoardException;
import com.projectif.ooslibrary.member.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
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
@Order(1) // 어드바이스가 다수일 경우 순서 지정 필수
public class BoardExceptionController {

    @ExceptionHandler({NoSuchBoardException.class})
    public ResponseEntity<String> noSuchBoardException(NoSuchBoardException ex) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Error", "NoSuchBoardException");

        log.info("NoSuchBoardException Exception 발생 [MemberControllerAdvice] : {}", ex.getMessage());

        return new ResponseEntity<>(ex.getMessage(), headers, HttpStatus.BAD_REQUEST);
    }

}
