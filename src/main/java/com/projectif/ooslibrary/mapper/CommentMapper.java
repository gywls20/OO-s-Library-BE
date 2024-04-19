package com.projectif.ooslibrary.mapper;

import com.github.pagehelper.Page;
import com.projectif.ooslibrary.comment.domain.CommentVO;
import com.projectif.ooslibrary.comment.dto.CommentRequestDTO;
import com.projectif.ooslibrary.comment.dto.PageRequestDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CommentMapper {

    void insertComment(CommentRequestDTO commentRequestDTO);

    //댓글 수 카운팅 / return : 댓글 수
    int getCount();

    void updateComment(CommentRequestDTO commentRequestDTO);

    void deleteComment(Long comment_pk);

    List<CommentVO> getById(Long comment_pk);

    List<CommentVO> getComments(PageRequestDTO pageRequestDTO);
}
