package com.projectif.ooslibrary.board.repository;

import com.projectif.ooslibrary.board.domain.Board;
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

    private final JPAQueryFactory queryFactory;

    // 대충 계층형
    @Override
    public List<Board> findBoardList() {

        return queryFactory.selectFrom(board)
                .leftJoin(board.parent)
                .fetchJoin()
                .orderBy(
                        board.parent.boardPk.asc().nullsFirst(),
                        board.createdDate.asc()
                ).fetch();
    }

    // 페이징을 위한 카운트 쿼리
    @Override
    public long countBoardList() {
        return queryFactory.select(board.count())
                .from(board)
                .fetchOne();
    }
}
