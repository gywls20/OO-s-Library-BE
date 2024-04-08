package com.projectif.ooslibrary.board.service;

import com.projectif.ooslibrary.board.domain.Board;
import com.projectif.ooslibrary.board.repository.BoardRepository;
import com.projectif.ooslibrary.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    // 한건 조회
    public Board getBoard(Long boardPk) {
        return boardRepository.findById(boardPk).orElseThrow(() -> new RuntimeException("찾는 게시물이 존재하지 않습니다"));
    }

    // 여러건 조회
    public Page<Board> getBoardList(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    // 원글 등록

    /** 답글 등록
     *  1. 답글 insert 쿼리
     *  2. 원글 level++ update 쿼리
     */

    // 글 수정

    // 글 삭제 (isDeleted == 1)
}
