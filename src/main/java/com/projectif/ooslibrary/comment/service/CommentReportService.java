package com.projectif.ooslibrary.comment.service;

import com.projectif.ooslibrary.comment.domain.CommentReportVO;
import com.projectif.ooslibrary.comment.dto.CommentReportRequestDTO;

import java.util.List;

public interface CommentReportService {
    List<CommentReportVO> listReportComment(CommentReportRequestDTO commentReportDTO);

    boolean insertReportComment(CommentReportRequestDTO commentReportRequestDTO);

    boolean updateReportComment(CommentReportRequestDTO commentReportRequestDTO);

    boolean deleteReportComment(Long commentPk, Long memberPk);
}
