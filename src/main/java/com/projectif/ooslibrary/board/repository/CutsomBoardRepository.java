package com.projectif.ooslibrary.board.repository;

import com.projectif.ooslibrary.board.domain.Board;

import java.util.List;

public interface CutsomBoardRepository {

    List<Board> findBoardList();
    long countBoardList();
}
