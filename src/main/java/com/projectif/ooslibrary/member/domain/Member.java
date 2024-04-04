package com.projectif.ooslibrary.member.domain;

import com.projectif.ooslibrary.config.auditing.BaseEntity;
import com.projectif.ooslibrary.member.dto.MemberResponseDTO;
import com.projectif.ooslibrary.member.dto.MemberUpdateRequestDTO;
import com.projectif.ooslibrary.my_library.domain.MyLibrary;
import com.projectif.ooslibrary.team.domain.Team;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(exclude = {"team", "myLibrary"})
public class Member extends BaseEntity implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_pk")
    private Long memberPk;
    @Column(unique = true, nullable = false)
    private String memberId;
    private String memberName;
    private String memberEmail;
    private String memberPassword;
    @Enumerated(EnumType.STRING)
    private Role role;

    // team_pk -> n:1 양방향
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_pk")
    private Team team;

    // my_library_pk -> 1:1 단방향
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "my_library_pk")
    private MyLibrary myLibrary;

    private String memberProfileImg;
    private Integer memberGender;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add((GrantedAuthority) () -> role.name());
        return collection;
    }

    // 사용자 id 반환 (고유 값, pk와 같은 식별자들)
    @Override
    public String getUsername() {
        return memberId;
    }

    @Override
    public String getPassword() {
        return memberPassword;
    }

    // 사용자 만료 여부 반환
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정 잠금 여부
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 패스워드 만료 여부
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정 사용 가능 여부
    @Override
    public boolean isEnabled() {
        return true;
    }

    // oauth2용 비밀번호 없는 빌더 -> oauth2로 등록한 회원은 비밀번호 NULL
    @Builder(builderMethodName = "oauth2Builder", buildMethodName = "buildOauth2")
    public Member(String memberId, String memberName, String memberEmail, Role role, String memberProfileImg, Integer memberGender) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberEmail = memberEmail;
        this.role = role;
        this.memberProfileImg = memberProfileImg;
        this.memberGender = memberGender;

    }

    // 기본 회원 빌더 -> 성별은 Integer 타입 매개변수, 비밀번호를 매개변수로 받음.
    @Builder(builderMethodName = "generalBuilder", buildMethodName = "buildGeneral")
    public Member(String memberId, String memberName, String memberEmail, String memberPassword, Integer memberGender) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberEmail = memberEmail;
        this.memberPassword = memberPassword;
        this.memberGender = memberGender;
        this.role = Role.USER;
    }

    // setter 대신 직관적으로 보이는 메서드들로 업데이트
    
    public void oauth2ChangeFields(String name, String picture, Role role) {
        this.memberName = name;
        this.memberProfileImg = picture;
        this.role = role;
    }

    public void memberUpdate(MemberUpdateRequestDTO dto) {
        this.memberName = dto.getMemberName();
        this.memberEmail = dto.getMemberEmail();
        this.memberPassword = dto.getMemberPassword();
        this.memberGender = dto.getMemberGender();
        this.memberProfileImg = dto.getMemberProfileImg();
    }

    public void memberDelete() {
        if (getIsDeleted() != 1) {
            this.setIsDeleted(1); // 삭제여부 플래그에 1 도입 -> 삭제됨을 의미.
        } else {

        }
    }

    public MemberResponseDTO convertToDTO() {
        return MemberResponseDTO.builder()
                .memberPk(this.memberPk)
                .memberId(this.memberId)
                .memberName(this.memberName)
                .memberEmail(this.memberEmail)
                .memberPassword(this.memberPassword)
                .memberGender(this.memberGender)
                .memberProfileImg(this.memberProfileImg)
                .build();

    }
}
