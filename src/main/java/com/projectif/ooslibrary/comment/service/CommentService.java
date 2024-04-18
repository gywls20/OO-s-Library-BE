package com.projectif.ooslibrary.comment.service;

import com.projectif.ooslibrary.comment.domain.CommentVO;
import com.projectif.ooslibrary.comment.dto.CommentRequestDTO;
import com.projectif.ooslibrary.comment.dto.PageInfo;

import java.util.List;
import java.util.Map;

public interface CommentService {
    //코멘트 등록
    void insertComment(CommentRequestDTO commentRequestDTO);

    //코멘트 리스트 조회(페이징 처리)
    //PageInfo<CommentVO> getComments(int pageIndex, int pageSize,Long book_pk, Long my_library_pk);
    List<CommentVO> getComments(Long bookPk, Long my_library_pk);

    //코멘트 등록
    void updateComment(CommentRequestDTO commentRequestDTO);

    void deleteComment(Long comment_pk);

    List<CommentVO> getById(Long comment_pk);

    //코멘트 삭제


}
