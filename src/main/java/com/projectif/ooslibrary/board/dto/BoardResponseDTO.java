package com.projectif.ooslibrary.board.dto;

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
//    private Long parent_pk;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
