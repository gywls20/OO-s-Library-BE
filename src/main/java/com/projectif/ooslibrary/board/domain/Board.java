package com.projectif.ooslibrary.board.domain;

import com.projectif.ooslibrary.board.dto.BoardResponseDTO;
import com.projectif.ooslibrary.config.auditing.BaseEntity;
import com.projectif.ooslibrary.member.domain.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString(exclude = {"member"})
@Table(name = "board")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_pk")
    private Long boardPk;
    private String boardTitle;
    private String boardCategory;
    private String boardContent;
    // 단방향 매핑
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_pk")
    private Member member;
    // Query용 댓글 그룹 모음 -> 최상위 원글의 board_pk
    private Long boardGroup;
    // 부모 원글
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="parent_pk")
    Board parent;
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<Board> child = new ArrayList<>();
    private Integer level;
    private LocalDateTime modifiedDate;

    // 생성자
    public Board(String boardTitle, String boardCategory, String boardContent, Member member, Board parent) {
        this.boardTitle = boardTitle;
        this.boardCategory = boardCategory;
        this.boardContent = boardContent;
        this.member = member;
        this.modifiedDate = LocalDateTime.now();
        this.parent = parent;
        if (parent == null) {
            this.level = 0;
        } else {
            this.level = parent.getLevel() + 1;
        }
    }

    // 업데이트 메서드
    public void changeBoard(String boardTitle, String boardCategory, String boardContent) {
        this.boardTitle = boardTitle;
        this.boardCategory = boardCategory;
        this.boardContent = boardContent;
        this.modifiedDate = LocalDateTime.now();
    }

    // 쿼리용 댓글 그룹 넣기
    public void addBoardGroup(Long boardGroup) {
        this.boardGroup = boardGroup;
    }
}
