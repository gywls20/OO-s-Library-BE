package com.projectif.ooslibrary.board.domain;

import com.projectif.ooslibrary.config.auditing.BaseEntity;
import com.projectif.ooslibrary.member.domain.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

@Entity
@Getter
@ToString(exclude = {"member"})
@Table(name = "board")
public class Board extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardPk;
    private String boardTitle;
    private String boardCategory;
    private String boardContent;
    // 단방향 매핑
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_pk")
    private Member member;
    
}
