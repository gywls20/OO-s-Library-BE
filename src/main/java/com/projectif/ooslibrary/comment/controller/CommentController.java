package com.projectif.ooslibrary.comment.controller;

import com.projectif.ooslibrary.comment.domain.CommentVO;
import com.projectif.ooslibrary.comment.dto.CommentRequestDTO;
import com.projectif.ooslibrary.comment.dto.PageInfo;
import com.projectif.ooslibrary.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

//@Slf4j
@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    //코멘드 생성
    @PostMapping("")
    public CommentVO insertComment(@RequestBody CommentRequestDTO commentRequestDTO) {
        Long comment_pk = commentService.insertComment(commentRequestDTO);
        return commentService.getCommentsById(comment_pk);
    }
    //전체 리스트로 불러오기
    /*@GetMapping("/comment")
    public List<CommentVO> getComments() {
        return commentService.getComments();
    }*/
    @GetMapping("")
    public PageInfo<CommentVO> getComments(@RequestParam(defaultValue = "1") int pageIndex,
                                           @RequestParam(defaultValue = "10") int pageSize) {
        return commentService.getComments(pageIndex, pageSize);
    }
    //멤버 아이디로 검색
    @GetMapping("/{member_pk}")
    public CommentVO getCommentsById(@PathVariable("member_pk") Long member_pk) {
        return commentService.getCommentsById(member_pk);
    }
    //코멘트 수정
    @PutMapping("/{comment_pk}")
    public CommentVO updateComment(@PathVariable("comment_pk") Long comment_pk,
                                   @RequestBody CommentRequestDTO commentRequestDTO) {
        commentService.updateComment(commentRequestDTO);
        return commentService.getCommentsById(comment_pk);
    }
    //코멘트 삭제
    @DeleteMapping("/{comment_pk}")
    public Long deleteComment(@PathVariable("comment_pk") Long comment_pk) {
        return commentService.deleteComment(comment_pk);
    }

}
