package com.projectif.ooslibrary.book_calendar.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectif.ooslibrary.book_calendar.Book_calendarDTO;
import com.projectif.ooslibrary.book_calendar.service.Book_calendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class Book_calendarController {

    private final Book_calendarService bookCalendarService;
    @Autowired
    private ObjectMapper objectMapper;
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

    // 북캘린더 페이지 이동
    @GetMapping("/calendar/{myLibrary}")
    public String bookCalendar(@PathVariable Long myLibrary , Model model){

        List<Book_calendarDTO> bookCalendars = bookCalendarService.getBookCalendar(myLibrary);

        try {
            String bookCalendarsJson = objectMapper.writeValueAsString(bookCalendars);

            model.addAttribute("bookCalendarListJson", bookCalendarsJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "library/bookCalendar";
    }



}
