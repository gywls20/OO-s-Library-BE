package com.projectif.ooslibrary.board.controller;

import com.projectif.ooslibrary.board.domain.Board;
import com.projectif.ooslibrary.board.dto.BoardResponseDTO;
import com.projectif.ooslibrary.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/{boardPk}")
    public Board getBoard(@PathVariable("boardPk") Long boardPk) {
        return boardService.getBoard(boardPk);
    }

    // 예) /boards?page=0&size=3&sort=id,desc&sort=boardTitle,desc
    // @PageableDefault(size = 10, page = 0, sort = "boardPk", direction = Sort.Direction.DESC) Pageable pageable, Model model
    @GetMapping("")
    public String getBoardList(Model model) {
        List<Board> boardList = boardService.getBoardList();
        List<BoardResponseDTO> list = boardList.stream().map(board ->
            BoardResponseDTO.builder()
                    .boardPk(board.getBoardPk())
                    .boardTitle(board.getBoardTitle())
                    .boardContent(board.getBoardContent())
                    .boardCategory(board.getBoardCategory())
                    .memberName(board.getMember().getMemberName())
                    .createdDate(board.getCreatedDate())
                    .modifiedDate(board.getModifiedDate())
                    .build()
        ).toList();

        for (BoardResponseDTO board : list) {
            log.info("board : {}", board.getBoardPk());
        }

        model.addAttribute("boardList", list);

        return "boards/boardList";
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
