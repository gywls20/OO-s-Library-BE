package com.projectif.ooslibrary.board.repository;

import com.projectif.ooslibrary.board.domain.Board;
import com.projectif.ooslibrary.board.domain.QBoard;
import com.querydsl.core.QueryFactory;
import com.querydsl.core.types.QBean;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.projectif.ooslibrary.board.domain.QBoard.*;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CutsomBoardRepositoryImpl implements CutsomBoardRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Board> findBoardList() {

        return jpaQueryFactory.selectFrom(board)
                .leftJoin(board.parent)
                .fetchJoin()
                .orderBy(
                        board.parent.boardPk.asc().nullsFirst(),
                        board.createdDate.asc()
                ).fetch();

    }
}
