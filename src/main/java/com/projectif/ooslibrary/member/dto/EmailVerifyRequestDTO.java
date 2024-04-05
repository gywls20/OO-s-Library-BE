package com.projectif.ooslibrary.member.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class EmailVerifyRequestDTO {
    private String name;
    private String email;
}
