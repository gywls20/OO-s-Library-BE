package com.projectif.ooslibrary.member.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Mail {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mailPk;
    private String code;
    private String email;
    private byte isVerified = 0;
    private LocalDateTime createdDate = LocalDateTime.now();

    public Mail(String code, String email) {
        this.code = code;
        this.email = email;
    }
}
