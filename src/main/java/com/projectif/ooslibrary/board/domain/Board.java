package com.projectif.ooslibrary.board.domain;

import com.projectif.ooslibrary.config.auditing.BaseEntity;
import com.projectif.ooslibrary.member.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString(exclude = {"member"})
@Table(name = "board")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    public Board(String boardTitle, String boardCategory, String boardContent, Member member, Board parent, Integer level, LocalDateTime modifiedDate) {
        this.boardTitle = boardTitle;
        this.boardCategory = boardCategory;
        this.boardContent = boardContent;
        this.member = member;
        this.modifiedDate = modifiedDate;
        this.parent = parent;
        if (parent == null) {
            this.level = 0;
        } else {
            this.level = parent.getLevel() + 1;
        }
    }

    // 연관관계 편의 메서드



}
