package com.projectif.ooslibrary.admin.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemberVO {

    private String member_id;
    private String member_name;
    private String member_email;
    private LocalDateTime created_date;
    private int is_deleted;
    private int member_gender;


}
