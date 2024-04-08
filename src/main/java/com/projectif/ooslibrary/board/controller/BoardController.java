package com.projectif.ooslibrary.board.controller;

import com.projectif.ooslibrary.board.domain.Board;
import com.projectif.ooslibrary.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/{boardPk}")
    public Board getBoard(@PathVariable("boardPk") Long boardPk) {
        return boardService.getBoard(boardPk);
    }

    // 예) /boards?page=0&size=3&sort=id,desc&sort=boardTitle,desc
    @GetMapping("")
    public Page<Board> getBoardList(@PageableDefault(size = 20, page = 0, sort = "boardPk", direction = Sort.Direction.DESC) Pageable pageable) {
        return boardService.getBoardList(pageable);
    }

    @PostMapping("")
    public String insertBoard(@RequestBody Board board) {
        return "문의 게시판 등록 미구현";
    }

    @PutMapping("/{boardPk}")
    public String updateBoard(@PathVariable("boardPk") Long boardPk, @RequestBody Board board) {
        return "문의 게시판 수정 미구현";
    }

    @DeleteMapping("/{boardPk}")
    public String deleteBoard(@PathVariable("boardPk") Long boardPk) {
        return "문의 게시판 삭제 미구현";
    }
}
