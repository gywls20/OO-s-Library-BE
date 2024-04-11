package com.projectif.ooslibrary.comment.service;

import com.projectif.ooslibrary.comment.domain.CommentVO;
import com.projectif.ooslibrary.comment.dto.CommentRequestDTO;
import com.projectif.ooslibrary.comment.dto.PageInfo;

import java.util.List;

public interface CommentService {
    //코멘트 등록
    Long insertComment(CommentRequestDTO commentRequestDTO);

    //코멘트 리스트 조회(페이징 처리)
    //List<CommentVO> getComments();
    PageInfo<CommentVO> getComments(int pageIndex, int pageSize,Long book_pk, Long my_library_pk);

    //특정 아이디로 코멘트 조회
    CommentVO getCommentsById(Long memberPk);

    //코멘트 등록
    void updateComment(CommentRequestDTO commentRequestDTO);

    //코멘트 삭제
    Long deleteComment(Long comment_pk);

}
