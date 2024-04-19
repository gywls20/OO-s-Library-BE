package com.projectif.ooslibrary.comment.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.projectif.ooslibrary.comment.domain.CommentVO;
import com.projectif.ooslibrary.comment.dto.CommentRequestDTO;
import com.projectif.ooslibrary.comment.dto.PageRequestDTO;
import com.projectif.ooslibrary.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements  CommentService{
    private final CommentMapper commentMapper;

    //코멘트 등록
    @Override
    public void insertComment(CommentRequestDTO commentRequestDTO) {
        commentMapper.insertComment(commentRequestDTO);
        //commentRequestDTO.getComment_pk();
    }

//    @Override
//    public List<CommentVO> getComments(Long bookPk, Long my_library_pk) {
//        return commentMapper.getComments(bookPk, my_library_pk);
//    }

    //코멘트 수정
    @Override
    public void updateComment(CommentRequestDTO commentRequestDTO) {
        commentMapper.updateComment(commentRequestDTO);
    }

    //코멘트 삭제
    @Override
    public void deleteComment(Long comment_pk) {
        commentMapper.deleteComment(comment_pk);
    }
    //코멘트 수정에 필요한 정보
    @Override
    public List<CommentVO> getById(Long comment_pk) {
        return commentMapper.getById(comment_pk);
    }

    @Override
    public List<CommentVO> getComments(PageRequestDTO pageRequestDTO) {
        PageHelper.startPage(pageRequestDTO.getPageNum(), pageRequestDTO.getPageSize());
        return commentMapper.getComments(pageRequestDTO);
    }


}

