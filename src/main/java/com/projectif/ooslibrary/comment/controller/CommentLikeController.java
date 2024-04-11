package com.projectif.ooslibrary.comment.controller;

import com.projectif.ooslibrary.comment.domain.CommentLikeVO;
import com.projectif.ooslibrary.comment.dto.CommentLikeRequestDTO;
import com.projectif.ooslibrary.comment.service.CommentLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentLikeController {
    private final CommentLikeService commentLikeService;

    //특정 코멘트의 좋아요 추가
    @PostMapping("/likes")
    public void likeComment(@RequestBody CommentLikeRequestDTO commentLikeRequestDTO) {;
        commentLikeService.likeComment(commentLikeRequestDTO);
    }

    //특정 코멘트의 좋아요 수
    @GetMapping("/likes/{comment_pk}")
    public int getLikeComment(@PathVariable Long comment_pk) {
        return commentLikeService.getLikeComment(comment_pk);
    }

    //코멘트의 전체 좋아요 리스트
    @GetMapping("/likes/list/{comment_pk}")
    public List<CommentLikeVO> listLikeComment(@PathVariable Long comment_pk) {
        return commentLikeService.listLikeComment(comment_pk);
    }
    //특정 코멘트의 좋아요 취소
    @DeleteMapping("/likes")
    public void deleteLikeComment(@RequestBody Map<String, Long> likeComment) {
        Long comment_pk = likeComment.get("comment_pk");
        Long member_pk = likeComment.get("member_pk");
        commentLikeService.deleteLikeComment(comment_pk, member_pk);
    }
}
