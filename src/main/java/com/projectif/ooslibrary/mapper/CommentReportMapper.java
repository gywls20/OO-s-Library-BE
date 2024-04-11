package com.projectif.ooslibrary.mapper;

import com.projectif.ooslibrary.comment.domain.CommentReportVO;
import com.projectif.ooslibrary.comment.dto.CommentReportRequestDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentReportMapper {
    List<CommentReportVO> listReportComment(CommentReportRequestDTO commentReportDTO);

    void insertReportComment(CommentReportRequestDTO commentReportRequestDTO);

    void add_total_report(CommentReportRequestDTO commentReportRequestDTO);

    void updateReportComment(CommentReportRequestDTO commentReportRequestDTO);

    void deleteReportComment(Long comment_pk, Long member_pk);

    void remove_total_report(Long comment_pk);
}
