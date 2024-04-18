package com.projectif.ooslibrary.comment.controller;

import com.projectif.ooslibrary.comment.domain.CommentVO;
import com.projectif.ooslibrary.comment.dto.CommentRequestDTO;
import com.projectif.ooslibrary.comment.dto.PageInfo;
import com.projectif.ooslibrary.comment.service.CommentService;
import com.projectif.ooslibrary.member.exception.SessionMemberNotMatchException;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

//@Slf4j
@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    //코멘트 생성
    @PostMapping("/comments/insert")
    public ResponseEntity<?> insertComment(CommentRequestDTO commentRequestDTO) {

         commentService.insertComment(commentRequestDTO);
         return ResponseEntity.ok().build();
    }

//    @GetMapping("/{bookPk}")
//    @ResponseBody
//    public String getComments(@PathVariable Long bookPk,
//                              Model model) {
//        List<CommentVO> commentList = commentService.getComments(bookPk);
//        model.addAttribute("commentList", commentList);
//        model.addAttribute("book_pk", bookPk);
//        return "members/myLibrary";
//    }
    //코멘트 리스트
@GetMapping("/comments/{bookPk}/{my_library_pk}")
@ResponseBody
public List<CommentVO> getComments(@PathVariable Long bookPk,
                                   @PathVariable Long my_library_pk) {
    return commentService.getComments(bookPk, my_library_pk);
}

    //코멘트 조회(+ 페이징)
//    @GetMapping("")
//    public PageInfo<CommentVO> getComments(@RequestParam(defaultValue = "1") int pageIndex,
//                                           @RequestParam(defaultValue = "10") int pageSize,
//                                           @RequestBody Map<String, Long> comment) {
//        Long book_pk = comment.get("book_pk");
//        Long my_library_pk = comment.get("my_library_pk");
//        return commentService.getComments(pageIndex, pageSize, book_pk, my_library_pk);
//    }

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
