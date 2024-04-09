package com.projectif.ooslibrary.board.service;

import com.projectif.ooslibrary.board.domain.Board;
import com.projectif.ooslibrary.board.exception.NoSuchBoardException;
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
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    // 한건 조회
    public Board getBoard(Long boardPk) {
        return boardRepository.findById(boardPk).orElseThrow(() -> new NoSuchBoardException("찾는 게시물이 존재하지 않습니다"));
    }

    // 여러건 조회 -> 간단 페이징
    public Page<Board> getBoardList(Pageable pageable) {
        return boardRepository.findAllByIsDeletedIs(0, pageable);
    }

    // 원글 등록
    @Transactional
    public boolean insertBoard(Board board) {
        Board saved = boardRepository.save(board);
        if (saved.getBoardPk() == null) {
            throw new RuntimeException("게시물 저장을 실패하였습니다");
        }
        return true;
    }

    // 글 수정
    @Transactional
    public boolean updateBoard(Long boardPk, Board board) {
        Board findBoard = boardRepository.findById(boardPk)
                .orElseThrow(() -> new NoSuchBoardException("찾는 게시물이 존재하지 않습니다"));
        // dirty checking
        findBoard.changeBoard(board.getBoardTitle(), board.getBoardCategory(), board.getBoardContent());
        return true;
    }

    // 글 삭제 (isDeleted == 1)
    @Transactional
    public boolean deleteBoard(Long boardPk) {
        Board findBoard = boardRepository.findById(boardPk)
                .orElseThrow(() -> new NoSuchBoardException("찾는 게시물이 존재하지 않습니다"));
        findBoard.setIsDeleted(1);
        // 더티 체킹 -> 삭제 플래그 처리.
        return true;
    }
}
