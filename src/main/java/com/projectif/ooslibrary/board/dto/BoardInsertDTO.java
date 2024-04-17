package com.projectif.ooslibrary.board.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BoardInsertDTO {

    @NotBlank
    private String boardCategory;
    @NotBlank
    private String boardTitle;
    @NotBlank
    private String boardContent;
    private Long memberPk;

}
