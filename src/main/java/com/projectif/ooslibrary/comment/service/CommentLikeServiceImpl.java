package com.projectif.ooslibrary.comment.service;

import com.projectif.ooslibrary.comment.domain.CommentLikeVO;
import com.projectif.ooslibrary.mapper.CommentLikeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentLikeServiceImpl implements  CommentLikeService{
    private final CommentLikeMapper commentLikeMapper;

    @Override
    public void likeComment(Long comment_pk, Long member_pk) {
        commentLikeMapper.likeComment(comment_pk, member_pk);
        //좋아요 추가 시 total_like 증가
        commentLikeMapper.add_total_like(comment_pk, member_pk);

    }

    @Override
    public int getLikeComment(Long comment_pk) {
        return commentLikeMapper.getLikeComment(comment_pk);
    }

    @Override
    public List<CommentLikeVO> listLikeComment(Long comment_pk) {
        return commentLikeMapper.listLikeComment(comment_pk);
    }

    @Override
    public void deleteLikeComment(Long comment_pk, Long member_pk) {
        commentLikeMapper.deleteLikeComment(comment_pk, member_pk);
        //좋아요 취소 시 total_like 감소
        commentLikeMapper.remove_total_like(comment_pk);
    }
}
