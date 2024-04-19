package com.projectif.ooslibrary.board.repository;

import com.projectif.ooslibrary.board.domain.Board;
import com.projectif.ooslibrary.board.dto.BoardResponseDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.projectif.ooslibrary.board.domain.QBoard.*;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CutsomBoardRepositoryImpl implements CutsomBoardRepository {

    private final JPAQueryFactory queryFactory;

    // 대충 계층형
    @Override
    public List<BoardResponseDTO> findBoardList(Pageable pageable) {

        return queryFactory.select(
                        Projections.constructor(
                                BoardResponseDTO.class,
                                board.boardPk,
                                board.boardTitle,
                                board.boardCategory,
                                board.boardContent,
                                board.member.memberName,
                                board.member.memberPk,
                                board.level,
                                board.createdDate,
                                board.modifiedDate
                        )
                )
                .from(board)
                .where(
                        isDeletedIs0()
                )
                .orderBy(
                        board.boardGroup.desc(),
                        board.level.asc(),
                        board.createdDate.desc()
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    private BooleanExpression isDeletedIs0() {
//        return StringUtils.hasText() ? board.isDeleted.eq(0) : null;
        return board.isDeleted.eq(0);
    }

    // 페이징을 위한 카운트 쿼리
    @Override
    public Long countBoardList() {
        return queryFactory.select(board.count())
                .from(board)
                .fetchOne();
    }

    // 계층형 QueryDsl 페이징 처리
    @Override
    public Page<BoardResponseDTO> boardPage(Pageable pageable) {

        List<BoardResponseDTO> contents = findBoardList(pageable);
        long counts = countBoardList();

        return new PageImpl<>(contents, pageable, counts);
    }

}
