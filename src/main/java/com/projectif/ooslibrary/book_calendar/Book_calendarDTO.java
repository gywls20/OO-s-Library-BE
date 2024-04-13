package com.projectif.ooslibrary.book_calendar;

import com.projectif.ooslibrary.my_library.domain.BookPlus;
import com.projectif.ooslibrary.my_library.domain.MyLibrary;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Book_calendarDTO {
    private Long bookCalendarPk;
    private int bookCalendarReadPercent;
    private MyLibrary myLibrary;
    private String bookCalendarScheduleDate;
    private BookPlus bookPlus;


}
