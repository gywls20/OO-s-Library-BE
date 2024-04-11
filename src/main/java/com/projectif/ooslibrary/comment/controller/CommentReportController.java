package com.projectif.ooslibrary.comment.controller;

import com.projectif.ooslibrary.comment.domain.CommentReportVO;
import com.projectif.ooslibrary.comment.dto.CommentReportRequestDTO;
import com.projectif.ooslibrary.comment.service.CommentReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentReportController {
    private final CommentReportService commentReportService;

    //특정 코멘트 또는 멤버 신고 리스트
    @GetMapping("/report")
    public List<CommentReportVO> listReportComment(@RequestBody CommentReportRequestDTO commentReportDTO) {
        return commentReportService.listReportComment(commentReportDTO);
    }
    //신고 추가
    @PostMapping("/report")
    public ResponseEntity<String> insertReportComment(@RequestBody CommentReportRequestDTO commentReportRequestDTO) {
        boolean result =  commentReportService.insertReportComment(commentReportRequestDTO);
        if (result) {
            return ResponseEntity.ok("true");
        } else
            return  ResponseEntity.badRequest().body("false");
    }
    //신고 수정
    @PutMapping("/report")
    public ResponseEntity<String> updateReportComment(@RequestBody CommentReportRequestDTO commentReportRequestDTO){
        boolean result = commentReportService.updateReportComment(commentReportRequestDTO);
        if(result)
            return ResponseEntity.ok("true");
        else
            return ResponseEntity.badRequest().body("false");
    }
    //신고 삭제(코멘트 + 멤버아이디)
    @DeleteMapping("/report")
    public ResponseEntity<String> deleteReportComment(@RequestBody Map<String, Long> ReportRequest){
        Long comment_pk = ReportRequest.get("comment_pk");
        Long member_pk = ReportRequest.get("member_pk");
        boolean result =  commentReportService.deleteReportComment(comment_pk, member_pk);
        if(result)
            return ResponseEntity.ok("true");
        else
            return ResponseEntity.badRequest().body("false");
    }
}
