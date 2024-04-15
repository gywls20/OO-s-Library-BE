package com.projectif.ooslibrary.my_library;

import com.projectif.ooslibrary.book.domain.Book;
import com.projectif.ooslibrary.member.repository.MemberRepository;
import com.projectif.ooslibrary.my_library.domain.MyLibrary;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class My_libraryController {
    
    private final My_libraryService myLibraryService;

    // 내 서재 페이지 이동
    @GetMapping("/myLibrary/{myLibraryPk}")
    public String myLibrary(@PathVariable("myLibraryPk") Long myLibraryPk, Model model) {
        // 내 서재에 등록된 책 리스트 가져오기
        List<Book> myLibraryBooks = myLibraryService.getMyLibraryBooks(myLibraryPk);
        model.addAttribute("bookList", myLibraryBooks);
        return "members/myLibrary";
    }


    @PutMapping("/bookPlus")
    @ResponseBody
    public ResponseEntity<String> saveBookToLibrary(@RequestParam Long bookPk,
                                                    @RequestParam Long myLibraryPk) {
        myLibraryService.saveBookPlus(bookPk, myLibraryPk);
        return ResponseEntity.ok("Book saved to library successfully");
    }

}
