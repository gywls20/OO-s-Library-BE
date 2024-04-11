package com.projectif.ooslibrary.comment.service;

import com.projectif.ooslibrary.comment.domain.CommentVO;
import com.projectif.ooslibrary.comment.dto.CommentRequestDTO;
import com.projectif.ooslibrary.comment.dto.PageInfo;
import com.projectif.ooslibrary.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements  CommentService{
    private final CommentMapper commentMapper;

    //코멘트 등록
    @Override
    public Long insertComment(CommentRequestDTO commentRequestDTO) {
        commentMapper.insertComment(commentRequestDTO);
        return commentRequestDTO.getComment_pk();
    }
    //코멘트 리스트로 조회(페이징 처리)
    @Override
    public PageInfo<CommentVO> getComments(int pageIndex, int pageSize) {
        int totalCount = commentMapper.getCount();
        List<CommentVO> commentVOList = commentMapper.getComments((pageIndex - 1) * pageSize, pageSize);
        return new PageInfo<>(pageIndex, pageSize, totalCount, commentVOList);
    }

    /*@Override
    public List<CommentVO> getComments() {
        return commentMapper.getComments();
    }*/

    //특정 아이디로 코멘트 조회
    @Override
    public CommentVO getCommentsById(Long member_pk) {
        return commentMapper.getCommentsById(member_pk);
    }

    //코멘트 수정
    @Override
    public void updateComment(CommentRequestDTO commentRequestDTO) {
        commentMapper.updateComment(commentRequestDTO);
    }

    //코멘트 삭제
    @Override
    public Long deleteComment(Long comment_pk) {
        commentMapper.deleteComment(comment_pk);
        return comment_pk;
    }
    //

}
    //코멘드 등록

