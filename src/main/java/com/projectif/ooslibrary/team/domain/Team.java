package com.projectif.ooslibrary.team.domain;

import com.projectif.ooslibrary.config.auditing.BaseEntity;
import com.projectif.ooslibrary.member.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Team extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_pk")
    private Long teamPk;
    @Column(length = 500)
    private String teamName;
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();
}
