package com.projectif.ooslibrary.board.repository;

import com.projectif.ooslibrary.board.domain.Board;
import com.projectif.ooslibrary.board.dto.BoardResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CutsomBoardRepository {

    List<BoardResponseDTO> findBoardList(Pageable pageable);
    Long countBoardList();
    Page<BoardResponseDTO> boardPage(Pageable pageable);
}
