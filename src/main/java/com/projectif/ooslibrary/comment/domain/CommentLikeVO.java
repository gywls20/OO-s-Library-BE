package com.projectif.ooslibrary.comment.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CommentLikeVO {
    private Long comment_like_pk;       //좋아요 수
    private Long comment_pk;            //코멘트 번호
    private Long member_pk;             //회원 번호
    private LocalDateTime created_date; //좋아요 한 날짜
    private short is_deleted;           //좋아요 취소 여부

}
