package com.projectif.ooslibrary.comment.controller;

import com.projectif.ooslibrary.comment.domain.CommentVO;
import com.projectif.ooslibrary.comment.dto.CommentRequestDTO;
import com.projectif.ooslibrary.comment.dto.PageRequestDTO;
import com.projectif.ooslibrary.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Slf4j
@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    //코멘트 생성
    @PostMapping("/comments/insert")
    @ResponseBody
    public ResponseEntity<String> insertComment(CommentRequestDTO commentRequestDTO) {
        commentService.insertComment(commentRequestDTO);
        return ResponseEntity.ok().build();
    }
    //코멘트 리스트(페이징 처리)
    @PostMapping("/comments/list")
    @ResponseBody
    public ResponseEntity<List<CommentVO>> list(@RequestBody PageRequestDTO pageRequestDTO) throws Exception {
        System.out.println(pageRequestDTO);
        List<CommentVO> comments = commentService.getComments(pageRequestDTO);
        System.out.println(comments);
        //html - body로 데이터 이동
        return ResponseEntity.ok().body(comments);
    }

    //코멘트 수정
    @GetMapping("/comments/updateComment")
    public String updateComment(@RequestParam("comment_pk") Long comment_pk,
                                Model model) {
//        if (id != session.getAttribute("pk")) {
//            throw new SessionMemberNotMatchException("접근이 허용되지 않는 정보입니다");
//        }
        System.out.println(comment_pk);
        //List<CommentVO> commentList = commentService.getById(comment_pk);
        CommentVO comment = commentService.getById(comment_pk).get(0);
        System.out.println(comment);
        model.addAttribute("comment", comment);
        return "members/commentUpdate";
    }
    @PostMapping("/comments/updateComment")
    public ResponseEntity<?> updateComment(CommentRequestDTO commentRequestDTO) {
        commentService.updateComment(commentRequestDTO);
        return ResponseEntity.ok().body("댓글이 성공적으로 업데이트되었습니다.");
    }

    //코멘트 삭제
    @DeleteMapping("/comments/delete/{comment_pk}")
    public ResponseEntity<String> deleteComment(@PathVariable Long comment_pk) {
        commentService.deleteComment(comment_pk);
        System.out.println("댓글 삭제: " + comment_pk);

        return ResponseEntity.ok().body("댓글이 삭제되었습니다.");
    }

}
