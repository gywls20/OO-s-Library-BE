package com.projectif.ooslibrary.book_calendar;

import com.projectif.ooslibrary.my_library.domain.BookPlus;
import com.projectif.ooslibrary.my_library.domain.MyLibrary;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Book_calendarDTO {

    private String bookTitle;
    private String coverImagePath;

    private int bookCalendarReadPercent;
    private LocalDateTime bookCalendarScheduleDate;

    //이건 왜 있어야하는거지?
    public Book_calendarDTO(String bookTitle, String coverImagePath, int bookCalendarReadPercent, LocalDateTime bookCalendarScheduleDate) {
        this.bookTitle = bookTitle;
        this.coverImagePath = coverImagePath;
        this.bookCalendarReadPercent = bookCalendarReadPercent;
        this.bookCalendarScheduleDate = bookCalendarScheduleDate;
    }

}
