package com.projectif.ooslibrary.board.repository;

import com.projectif.ooslibrary.board.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CutsomBoardRepository {

    List<Board> findBoardList();
    Long countBoardList();
    Page<Board> boardPage(Pageable pageable);
}
