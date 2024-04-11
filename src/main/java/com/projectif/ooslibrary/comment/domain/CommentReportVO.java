package com.projectif.ooslibrary.comment.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CommentReportVO {
    private Long report_pk;
    private String report_category;
    private String report_name;
    private String report_content;
    private Long comment_pk;
    private Long member_pk;
    private LocalDateTime created_date;
}
