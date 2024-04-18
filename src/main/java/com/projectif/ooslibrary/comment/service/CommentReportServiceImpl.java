package com.projectif.ooslibrary.comment.service;

import com.projectif.ooslibrary.comment.domain.CommentReportVO;
import com.projectif.ooslibrary.comment.dto.CommentReportRequestDTO;
import com.projectif.ooslibrary.mapper.CommentReportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentReportServiceImpl implements CommentReportService{
    private final CommentReportMapper commentReportMapper;

    //신고 리스트
    @Override
    public List<CommentReportVO> listReportComment(CommentReportRequestDTO commentReportDTO) {
        return commentReportMapper.listReportComment(commentReportDTO);
    }
    //신고 등록
    @Override
    public void insertReportComment(CommentReportRequestDTO commentReportRequestDTO) {
        commentReportMapper.insertReportComment(commentReportRequestDTO);
        commentReportMapper.add_total_report(commentReportRequestDTO);
    }
    //신고 수정
    @Override
    public boolean updateReportComment(CommentReportRequestDTO commentReportRequestDTO) {
        commentReportMapper.updateReportComment(commentReportRequestDTO);
        return true;
    }
    //신고 삭제
    @Override
    public boolean deleteReportComment(Long comment_pk, Long member_pk) {
        commentReportMapper.deleteReportComment(comment_pk, member_pk);
        commentReportMapper.remove_total_report(comment_pk);
        return true;
    }


}
