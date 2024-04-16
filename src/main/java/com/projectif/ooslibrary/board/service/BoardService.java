package com.projectif.ooslibrary.board.service;

import com.projectif.ooslibrary.board.domain.Board;
import com.projectif.ooslibrary.board.dto.BoardInsertDTO;
import com.projectif.ooslibrary.board.dto.BoardUpdateDTO;
import com.projectif.ooslibrary.board.exception.NoSuchBoardException;
import com.projectif.ooslibrary.board.repository.BoardRepository;
import com.projectif.ooslibrary.member.domain.Member;
import com.projectif.ooslibrary.member.exception.NoSuchMemberException;
import com.projectif.ooslibrary.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    // 한건 조회
    public Board getBoard(Long boardPk) {
        return boardRepository.findByBoardPkAndIsDeletedIs(boardPk, 0).orElseThrow(() -> new NoSuchBoardException("찾는 게시물이 존재하지 않습니다"));
    }

    // 여러건 조회 -> 간단 페이징
    public List<Board> getBoardList() {
//        return boardRepository.findAllByIsDeletedIs(0, pageable);
//        return boardRepository.boardPage(pageable);
        return boardRepository.findAllByIsDeletedIs(0);
    }

    // 원글 등록
    @Transactional
    public boolean insertBoard(BoardInsertDTO board) {
        Member member = memberRepository.findByIdNotDeleted(board.getMemberPk())
                .orElseThrow(() -> new NoSuchMemberException("회원이 존재하지 않습니다"));
        Board newBoard = new Board(board.getBoardTitle(), board.getBoardCategory(), board.getBoardContent(), member, null);
        Board saved = boardRepository.save(newBoard);
        if (saved.getBoardPk() == null) {
            throw new RuntimeException("게시물 저장을 실패하였습니다");
        }
        return true;
    }

    // 글 수정
    @Transactional
    public boolean updateBoard(Long boardPk, BoardUpdateDTO board) {
        Board findBoard = boardRepository.findByBoardPkAndIsDeletedIs(boardPk, 0)
                .orElseThrow(() -> new NoSuchBoardException("찾는 게시물이 존재하지 않습니다"));
        // dirty checking
        findBoard.changeBoard(board.getBoardTitle(), board.getBoardCategory(), board.getBoardContent());
        return true;
    }

    // 글 삭제 (isDeleted == 1)
    @Transactional
    public boolean deleteBoard(Long boardPk) {
        Board findBoard = boardRepository.findByBoardPkAndIsDeletedIs(boardPk, 0)
                .orElseThrow(() -> new NoSuchBoardException("찾는 게시물이 존재하지 않습니다"));
        findBoard.setIsDeleted(1);
        // 더티 체킹 -> 삭제 플래그 처리.
        return true;
    }
}
