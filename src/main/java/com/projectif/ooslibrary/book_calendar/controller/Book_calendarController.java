package com.projectif.ooslibrary.book_calendar.controller;

import com.projectif.ooslibrary.book_calendar.Book_calendarDTO;
import com.projectif.ooslibrary.book_calendar.service.Book_calendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class Book_calendarController {

    private final Book_calendarService bookCalendarService;

    @PostMapping("/readData")
    @ResponseBody
    public ResponseEntity<String> saveBookData(@RequestParam int percent,
                                               @RequestParam Long myLibraryPk,
                                               @RequestParam Long bookPlus){
        bookCalendarService.saveBookData(percent, myLibraryPk, bookPlus);
        return ResponseEntity.ok("이력저장 완료");
    }
    @GetMapping("/readData")
    @ResponseBody
    public List<Book_calendarDTO> getBookCalendar(@RequestParam Long myLibrary){

        return bookCalendarService.getBookCalendar(myLibrary);
    }

}
