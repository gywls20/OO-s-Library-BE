package com.projectif.ooslibrary.mapper;

import com.projectif.ooslibrary.comment.domain.CommentLikeVO;
import com.projectif.ooslibrary.comment.dto.CommentLikeRequestDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentLikeMapper {
    //좋아요 등록
    void likeComment(Long comment_pk, Long member_pk);
    //좋아요 수 증가
    void add_total_like(Long comment_pk, Long member_pk);
    //좋아요 수 리턴
    int getLikeComment(Long comment_pk);
    //특정 코멘트 좋아요 리스트
    List<CommentLikeVO> listLikeComment(Long comment_pk);
    //좋아요 취소
    void deleteLikeComment(Long comment_pk, Long member_pk);
    //좋아요 수 감소
    void remove_total_like(Long comment_pk);
}
