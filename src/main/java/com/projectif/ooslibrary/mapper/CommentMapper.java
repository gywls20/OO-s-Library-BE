package com.projectif.ooslibrary.mapper;

import com.projectif.ooslibrary.comment.domain.CommentVO;
import com.projectif.ooslibrary.comment.dto.CommentResponseDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    void insertComment(CommentResponseDTO commentResponseDTO);

    //댓글 리스트 조회 / return : 댓글 리스트
    //List<CommentVO> getComments();
    List<CommentVO> getComments(int offset, int limit);

    //댓글 수 카운팅 / return : 댓글 수
    int getCount();

    CommentVO getCommentsById(Long member_pk);

    void updateComment(CommentResponseDTO commentResponseDTO);

    void deleteComment(Long comment_pk);
}
