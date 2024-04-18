package com.projectif.ooslibrary.board.controller;

import com.projectif.ooslibrary.board.domain.Board;
import com.projectif.ooslibrary.board.dto.BoardInsertDTO;
import com.projectif.ooslibrary.board.dto.BoardResponseDTO;
import com.projectif.ooslibrary.board.dto.BoardUpdateDTO;
import com.projectif.ooslibrary.board.repository.BoardRepository;
import com.projectif.ooslibrary.board.service.BoardService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final HttpSession session;
    private final BoardRepository boardRepository;

    @GetMapping("/{boardPk}")
    public String getBoard(@PathVariable("boardPk") Long boardPk, Model model) {
        Board findBoard = boardService.getBoard(boardPk);
        BoardResponseDTO board = BoardResponseDTO.builder()
                .boardPk(findBoard.getBoardPk())
                .boardTitle(findBoard.getBoardTitle())
                .boardContent(findBoard.getBoardContent())
                .boardCategory(findBoard.getBoardCategory())
                .memberName(findBoard.getMember().getMemberName())
                .memberPk(findBoard.getMember().getMemberPk())
                .createdDate(findBoard.getCreatedDate())
                .modifiedDate(findBoard.getModifiedDate())
                .build();
        model.addAttribute("board", board);
        return "boards/boardOne";
    }

    // 예) /boards?page=0&size=3&sort=id,desc&sort=boardTitle,desc
    // @PageableDefault(size = 10, page = 0, sort = "boardPk", direction = Sort.Direction.DESC) Pageable pageable, Model model
    @GetMapping("")
    public String getBoardList(@PageableDefault(size = 5, page = 0, sort = "boardPk", direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        Page<BoardResponseDTO> boardPage = boardService.getBoardList(pageable);

//        Page<Board> boardPage = boardService.getBoardList(pageable);
//        Page<BoardResponseDTO> list = boardPage.map(board ->
//                BoardResponseDTO.builder()
//                        .boardPk(board.getBoardPk())
//                        .boardTitle(board.getBoardTitle())
//                        .boardContent(board.getBoardContent())
//                        .boardCategory(board.getBoardCategory())
//                        .memberName(board.getMember().getMemberName())
//                        .memberPk(board.getMember().getMemberPk())
//                        .level(board.getLevel())
//                        .createdDate(board.getCreatedDate())
//                        .modifiedDate(board.getModifiedDate())
//                        .build()
//        );

        int startPage = Math.max(1, boardPage.getPageable().getPageNumber() - 10);
        int endPage = Math.min(boardPage.getTotalPages(), boardPage.getPageable().getPageNumber() + 10);

//        model.addAttribute("boardList", list);
        model.addAttribute("boardList", boardPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "boards/boardList";
    }

    // 원글 등록 페이지
    @GetMapping("/insert")
    public String insertBoard(Model model) {

        if (session.getAttribute("pk") == null) {
            return "redirect:/login";
        }

        model.addAttribute("board", new BoardInsertDTO());

        return "boards/boardWriteForm";
    }

    @PostMapping("")
    public String insertBoard(@ModelAttribute("board") @Validated BoardInsertDTO board, BindingResult bindingResult, Model model) {

        Long pk = (Long) session.getAttribute("pk");
        if (pk != null) {
            board.setMemberPk(pk);
        }

        if (bindingResult.hasErrors()) {
            return "boards/boardWriteForm";
        }

        boardService.insertBoard(board);
        return "redirect:/boards";
    }

    // 글 수정 페이지 ㄱㄱ
    @GetMapping("/{boardPk}/update")
    public String updateBoardPage(@PathVariable("boardPk") Long boardPk, Model model) {
        Board findBoard = boardService.getBoard(boardPk);
        BoardResponseDTO board = BoardResponseDTO.builder()
                .boardPk(findBoard.getBoardPk())
                .boardTitle(findBoard.getBoardTitle())
                .boardContent(findBoard.getBoardContent())
                .boardCategory(findBoard.getBoardCategory())
                .memberName(findBoard.getMember().getMemberName())
                .memberPk(findBoard.getMember().getMemberPk())
                .createdDate(findBoard.getCreatedDate())
                .modifiedDate(findBoard.getModifiedDate())
                .build();
        model.addAttribute("board", board);
        return "boards/boardUpdateForm";
    }


    @PutMapping("/{boardPk}")
    @ResponseBody
    public boolean updateBoard(@PathVariable("boardPk") Long boardPk, @RequestBody BoardUpdateDTO board) {
        return boardService.updateBoard(boardPk, board);
    }

    @DeleteMapping("/{boardPk}")
    @ResponseBody
    public boolean deleteBoard(@PathVariable("boardPk") Long boardPk) {
        if (session.getAttribute("pk") == null) {
            log.info("세션이 null 이므로 삭제 불가능");
            return false;
        }
        return boardService.deleteBoard(boardPk);
    }

}
