package com.projectif.ooslibrary.my_library;

import com.projectif.ooslibrary.book.BookRepository;
import com.projectif.ooslibrary.book.domain.Book;
import com.projectif.ooslibrary.book_calendar.Book_calendarRepository;
import com.projectif.ooslibrary.member.repository.MemberRepository;
import com.projectif.ooslibrary.my_library.domain.BookPlus;
import com.projectif.ooslibrary.my_library.domain.MyLibrary;
import com.projectif.ooslibrary.my_library.repository.BookPlusRepository;
import com.projectif.ooslibrary.my_library.repository.MyLibraryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class My_libraryService {

    private final BookRepository bookRepository;
    private final BookPlusRepository bookPlusRepository;
    private final MyLibraryRepository myLibraryRepository;
    private  final Book_calendarRepository bookCalendarRepository;

    @Transactional
    public void saveBookPlus(Long bookPk, Long myLibraryPk) {

        Book book = bookRepository.findById(bookPk)
                .orElseThrow(() -> new RuntimeException("책이 존재하지 않습니다."));
        MyLibrary findMyLibrary = myLibraryRepository.findById(myLibraryPk)
                .orElseThrow(() -> new RuntimeException("내 서재가 존재하지 않습니다"));

        if(bookPlusRepository.existsByBookAndMyLibrary(book, findMyLibrary)) {
            throw new RuntimeException("이미 저장된 책입니다.");
        }
        BookPlus bookPlus = new BookPlus();
        bookPlus.setBook(book);
        bookPlus.setMyLibrary(findMyLibrary);

        bookPlusRepository.save(bookPlus);
    }

    @Transactional
    public void DeleteBookToLibrary(Long bookPk, Long myLibraryPk){
        Book book = bookRepository.findById(bookPk)
                .orElseThrow(() -> new RuntimeException("책이 존재하지 않습니다."));
        MyLibrary myLibrary = myLibraryRepository.findById(myLibraryPk)
                .orElseThrow(() -> new RuntimeException("내 서재가 존재하지 않습니다"));

        // BookPlus 엔티티를 찾습니다.
        BookPlus bookPlus = bookPlusRepository.findByBookAndMyLibrary(book, myLibrary)
                .orElseThrow(() -> new RuntimeException("해당 BookPlus가 존재하지 않습니다."));
        //BookPlus , myLibrary 를 이용해서 Book_calendar를 삭제한다.
        bookCalendarRepository.deleteByBookPlusAndMyLibrary(bookPlus,myLibrary);

        // 찾은 BookPlus 엔티티를 삭제합니다.
        bookPlusRepository.delete(bookPlus);
    }

    public List<Book> getMyLibraryBooks(Long myLibraryPk) {

        MyLibrary findMyLibrary = myLibraryRepository.findById(myLibraryPk)
                .orElseThrow(() -> new RuntimeException("내 서재가 존재하지 않습니다"));

        log.info(findMyLibrary.toString());

        List<BookPlus> bookPlusList = findMyLibrary.getBookPlusList();
        ArrayList<Book> books = new ArrayList<>();
        for (BookPlus bookPlus : bookPlusList) {
            books.add(bookPlus.getBook());
        }

        return books;
    }
}
