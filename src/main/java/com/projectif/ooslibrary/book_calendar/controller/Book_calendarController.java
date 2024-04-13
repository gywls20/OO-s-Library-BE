package com.projectif.ooslibrary.book_calendar.controller;

import com.projectif.ooslibrary.book_calendar.Book_calendarDTO;
import com.projectif.ooslibrary.book_calendar.service.Book_calendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class Book_calendarController {

    private final Book_calendarService bookCalendarService;

    @PostMapping("/ebookData")
    @ResponseBody
    public ResponseEntity<String> saveBookData(@RequestParam int percent,
                                               @RequestParam Long myLibraryPk,
                                               @RequestParam Long bookPlus){
        bookCalendarService.saveBookData(percent, myLibraryPk, bookPlus);
        return ResponseEntity.ok("이력저장 완료");
    }

//    public Book_calendarDTO getBookCalendar(@PathVariable Long book_pk){
//        return;
//    }

}
