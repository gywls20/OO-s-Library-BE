package com.projectif.ooslibrary.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Getter
@AllArgsConstructor
public class PageInfo<T> implements Serializable { //페이징 처리
    private static final long serialVersionUID = 1L;

    private final int pageIndex;    //페이지 번호
    private final int pageSize;     //페이지당 출력할 데이터 개수
    private final int totalCount;   //전체 페이지
    private final List<T> data;     //데이터 목록

    public int getTotalPage() {
        return (int) Math.ceil((double) totalCount / pageSize);
    }

}
