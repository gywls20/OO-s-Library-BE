package com.projectif.ooslibrary.comment.service;

import com.projectif.ooslibrary.comment.domain.CommentLikeVO;
import com.projectif.ooslibrary.comment.dto.CommentLikeRequestDTO;

import java.util.List;

public interface CommentLikeService {
    void likeComment(CommentLikeRequestDTO commentLikeRequestDTO);

    int getLikeComment(Long commentPk);

    List<CommentLikeVO> listLikeComment(Long commentPk);

    void deleteLikeComment(Long commentPk, Long memberPk);
}
