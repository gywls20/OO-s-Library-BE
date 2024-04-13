package com.projectif.ooslibrary.book_calendar;

import com.projectif.ooslibrary.book.domain.Book;
import com.projectif.ooslibrary.book_calendar.domain.Book_calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Book_calendarRepository extends JpaRepository<Book_calendar, Long> {

}
