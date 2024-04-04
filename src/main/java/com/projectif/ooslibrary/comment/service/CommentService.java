package com.projectif.ooslibrary.comment.service;

import com.projectif.ooslibrary.comment.domain.CommentVO;
import com.projectif.ooslibrary.comment.dto.CommentResponseDTO;
import com.projectif.ooslibrary.comment.dto.PageInfo;
import com.projectif.ooslibrary.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;

public interface CommentService {
    //코멘트 등록
    Long insertComment(CommentResponseDTO commentResponseDTO);

    //코멘트 리스트 조회(페이징 처리)
    //List<CommentVO> getComments();
    PageInfo<CommentVO> getComments(int pageIndex, int pageSize);

    //특정 아이디로 코멘트 조회
    CommentVO getCommentsById(Long memberPk);

    //코멘트 등록
    void updateComment(CommentResponseDTO commentResponseDTO);

    //코멘트 삭제
    Long deleteComment(Long comment_pk);
}
