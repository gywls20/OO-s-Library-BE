package com.projectif.ooslibrary.my_library.domain;

import com.projectif.ooslibrary.book_calendar.domain.BookCalendar;
import com.projectif.ooslibrary.member.domain.Member;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(exclude = {"member"})
public class MyLibrary {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "my_library_pk")
    private Long myLibraryPk;
    @Column(length = 500)
    private String myLibraryName;
    @OneToOne(mappedBy = "myLibrary")
    private Member member;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "myLibrary")
    private List<BookPlus> bookPlusList = new ArrayList<>();
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "myLibrary")
    private List<BookCalendar> bookCalendars = new ArrayList<>();

    @Builder
    public MyLibrary(String myLibraryName) {
        this.myLibraryName = myLibraryName;
    }
}
