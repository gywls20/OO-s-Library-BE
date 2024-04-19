package com.projectif.ooslibrary.comment.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.projectif.ooslibrary.comment.domain.CommentVO;
import com.projectif.ooslibrary.comment.dto.CommentRequestDTO;
import com.projectif.ooslibrary.comment.dto.PageRequestDTO;

import java.util.List;
import java.util.Map;

public interface CommentService {
    //코멘트 등록
    void insertComment(CommentRequestDTO commentRequestDTO);

    //코멘트 수정
    void updateComment(CommentRequestDTO commentRequestDTO);

    //코멘트 삭제
    void deleteComment(Long comment_pk);

    List<CommentVO> getById(Long comment_pk);
    //코멘트 조회
    List<CommentVO> getComments(PageRequestDTO pageRequestDTO);


    //코멘트 삭제


}
