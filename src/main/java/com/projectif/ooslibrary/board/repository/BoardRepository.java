package com.projectif.ooslibrary.board.repository;

import com.projectif.ooslibrary.board.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long>, CutsomBoardRepository {

    Optional<Board> findByIdAnAndIsDeletedIs(Long memberPk, Integer isDeleted);
    Page<Board> findAllByIsDeletedIs(Integer isDeleted, Pageable pageable);
}
