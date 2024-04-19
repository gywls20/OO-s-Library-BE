package com.projectif.ooslibrary.comment.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageRequestDTO {
    private Long bookPk;
    private Long myLibraryPk;
    private int pageNum; // 현재 페이지 번호
    private int pageSize; // 한 페이지당 보여줄 데이터 개수

    // 기본값 설정
    public PageRequestDTO() {
        this.pageNum = 1; // 기본 페이지 번호는 1
        this.pageSize = 3; // 기본 페이지 크기는 3
    }
}
