package com.projectif.ooslibrary.book_calendar.service;

import com.projectif.ooslibrary.book_calendar.Book_calendarRepository;
import com.projectif.ooslibrary.book_calendar.domain.Book_calendar;
import com.projectif.ooslibrary.my_library.domain.BookPlus;
import com.projectif.ooslibrary.my_library.domain.MyLibrary;
import com.projectif.ooslibrary.my_library.repository.BookPlusRepository;
import com.projectif.ooslibrary.my_library.repository.MyLibraryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class Book_calendarService {
    private final Book_calendarRepository book_calendarRepository;
    private final MyLibraryRepository myLibraryRepository;
    private final BookPlusRepository bookPlusRepository;

    @Transactional
    public void saveBookData(int percent, Long myLibraryPk, Long bookPlus){
        Book_calendar bookCalendar=new Book_calendar();
        bookCalendar.setBookCalendarReadPercent(percent);

        MyLibrary findMyLibrary = myLibraryRepository.findById(myLibraryPk)
                .orElseThrow(() -> new RuntimeException("내 서재가 존재하지 않습니다"));
        bookCalendar.setMyLibrary(findMyLibrary);
        BookPlus findBookPlus =  bookPlusRepository.findById(bookPlus)
                .orElseThrow(() -> new RuntimeException("내서재에 그런 책은 존재하지 않습니다"));
        bookCalendar.setBookPlus(findBookPlus);

        book_calendarRepository.save(bookCalendar);
    }
}
