package com.projectif.ooslibrary.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentLikeRequestDTO {
    private Long comment_pk;            //코멘트 번호
    private Long member_pk;
}
