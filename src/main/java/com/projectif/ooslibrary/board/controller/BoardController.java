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
    public Page<Board> getBoardList(@PageableDefault(size = 10, page = 0, sort = "boardPk", direction = Sort.Direction.DESC) Pageable pageable) {
        return boardService.getBoardList(pageable);
    }

    @PostMapping("")
    public boolean insertBoard(@RequestBody Board board) {
        return boardService.insertBoard(board);
    }

    @PutMapping("/{boardPk}")
    public boolean updateBoard(@PathVariable("boardPk") Long boardPk, @RequestBody Board board) {
        return boardService.updateBoard(boardPk, board);
    }

    @DeleteMapping("/{boardPk}")
    public boolean deleteBoard(@PathVariable("boardPk") Long boardPk) {
        return boardService.deleteBoard(boardPk);
    }
}
