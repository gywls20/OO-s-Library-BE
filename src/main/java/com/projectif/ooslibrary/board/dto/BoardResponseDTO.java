package com.projectif.ooslibrary.board.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
@Builder
public class BoardResponseDTO {

    private Long boardPk;
    private String boardTitle;
    private String boardCategory;
    private String boardContent;
    private String memberName;
    private Long memberPk;
    private Integer level;
//    private Long parent_pk;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @QueryProjection
    public BoardResponseDTO(Long boardPk, String boardTitle, String boardCategory, String boardContent, String memberName, Long memberPk, Integer level, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.boardPk = boardPk;
        this.boardTitle = boardTitle;
        this.boardCategory = boardCategory;
        this.boardContent = boardContent;
        this.memberName = memberName;
        this.memberPk = memberPk;
        this.level = level;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
