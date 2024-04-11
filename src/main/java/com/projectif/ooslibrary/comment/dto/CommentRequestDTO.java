package com.projectif.ooslibrary.comment.dto;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentRequestDTO { //등록, 수정
    private Long comment_pk;
    private Long member_pk;
    private String comment_title;
    private String comment_content;
}
