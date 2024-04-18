package com.projectif.ooslibrary.comment.service;

import com.projectif.ooslibrary.comment.domain.CommentLikeVO;
import com.projectif.ooslibrary.comment.dto.CommentLikeRequestDTO;

import java.util.List;

public interface CommentLikeService {
    //void likeComment(CommentLikeRequestDTO commentLikeRequestDTO);

    void likeComment(Long comment_pk, Long member_pk);

    int getLikeComment(Long commentPk);

    List<CommentLikeVO> listLikeComment(Long commentPk);

    void deleteLikeComment(Long comment_pk, Long member_pk);
}
