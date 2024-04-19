package com.projectif.ooslibrary.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CommentLikeRequestDTO {
    private Long comment_pk;            //코멘트 번호
    private Long member_pk;
}
