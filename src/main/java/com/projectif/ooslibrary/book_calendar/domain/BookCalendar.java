package com.projectif.ooslibrary.book_calendar.domain;

import com.projectif.ooslibrary.my_library.domain.BookPlus;
import com.projectif.ooslibrary.my_library.domain.MyLibrary;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity @Getter @NoArgsConstructor
@Setter
public class BookCalendar {

    @Id @Column(name = "book_calendar_pk")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookCalendarPk;

    @Column(name = "book_calendar_read_percent")
    private int bookCalendarReadPercent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="my_library_pk")
    private MyLibrary myLibrary;

    @CreationTimestamp
    private LocalDateTime bookCalendarScheduleDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="book_plus_pk")
    private BookPlus bookPlus;

    @CreationTimestamp
    private LocalDateTime created_date;
    private boolean is_deleted;

    @Builder
    public BookCalendar(Long bookCalendarPk, int bookCalendarReadPercent,
                            MyLibrary myLibrary, LocalDateTime bookCalendarScheduleDate,
                            BookPlus bookPlus){
        this.bookCalendarPk = bookCalendarPk;
        this.bookCalendarReadPercent = bookCalendarReadPercent;
        this.myLibrary = myLibrary;
        this.bookCalendarScheduleDate = bookCalendarScheduleDate;
        this.bookPlus = bookPlus;
    }
}
