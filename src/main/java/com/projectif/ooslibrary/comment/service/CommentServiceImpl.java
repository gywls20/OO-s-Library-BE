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
    public void insertComment(CommentRequestDTO commentRequestDTO) {
        commentMapper.insertComment(commentRequestDTO);
        //commentRequestDTO.getComment_pk();
    }

    @Override
    public List<CommentVO> getComments(Long bookPk, Long my_library_pk) {
        return commentMapper.getComments(bookPk, my_library_pk);
    }


    //코멘트 리스트로 조회(페이징 처리)
//    @Override
//    public PageInfo<CommentVO> getComments(int pageIndex, int pageSize, Long book_pk, Long my_library_pk) {
//        int totalCount = commentMapper.getCount();
//        List<CommentVO> commentVOList = commentMapper.getComments((pageIndex - 1) * pageSize, pageSize, book_pk, my_library_pk);
//        return new PageInfo<>(pageIndex, pageSize, totalCount, commentVOList);
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

}

