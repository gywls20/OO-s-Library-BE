package com.projectif.ooslibrary.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CommentReportRequestDTO {
    private String report_category;
    private String report_name;
    private String report_content;
    private Long comment_pk;
    private Long member_pk;

}
