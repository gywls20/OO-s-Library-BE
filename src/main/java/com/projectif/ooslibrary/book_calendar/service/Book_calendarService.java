package com.projectif.ooslibrary.book_calendar.service;

import com.projectif.ooslibrary.book_calendar.Book_calendarDTO;
import com.projectif.ooslibrary.book_calendar.Book_calendarRepository;
import com.projectif.ooslibrary.book_calendar.domain.BookCalendar;
import com.projectif.ooslibrary.my_library.domain.BookPlus;
import com.projectif.ooslibrary.my_library.domain.MyLibrary;
import com.projectif.ooslibrary.my_library.repository.BookPlusRepository;
import com.projectif.ooslibrary.my_library.repository.MyLibraryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class Book_calendarService {
    private final Book_calendarRepository book_calendarRepository;
    private final MyLibraryRepository myLibraryRepository;
    private final BookPlusRepository bookPlusRepository;

    @Transactional //이력 추가 기능
    public void saveBookData(int percent, Long myLibraryPk, Long bookPlus){
        BookCalendar bookCalendar=new BookCalendar();
        bookCalendar.setBookCalendarReadPercent(percent);

        MyLibrary findMyLibrary = myLibraryRepository.findById(myLibraryPk)
                .orElseThrow(() -> new RuntimeException("내 서재가 존재하지 않습니다"));
        bookCalendar.setMyLibrary(findMyLibrary);
        BookPlus findBookPlus =  bookPlusRepository.findById(bookPlus)
                .orElseThrow(() -> new RuntimeException("내서재에 그런 책은 존재하지 않습니다"));
        bookCalendar.setBookPlus(findBookPlus);

        book_calendarRepository.save(bookCalendar);
    }

    @Transactional //이력 리스트업 기능
    public List<Book_calendarDTO> getBookCalendar (Long myLibrary){

        return book_calendarRepository.findBookCalendarInfoByMyLibrary(myLibrary);
    }

    //book calendar page
    @Transactional
    public  List<Book_calendarDTO> BookCalendarpage(Long pk){
        return getBookCalendar(pk);
    }

}
