package com.projectif.ooslibrary.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookDTO> getBooks(@RequestParam(required = false) String category,
                                  @RequestParam(required = false, defaultValue = "asc") String sortOrder) {
        if (category != null) {
            return bookService.getBooksByCategory(category, sortOrder);
        } else {
            return bookService.getAllBooks(sortOrder);
        }
    }
    //ebook 페이지
    @GetMapping("/{book_pk}")
    public BookTextDTO getBookText(@PathVariable Long book_pk) {
        return bookService.getBookPath(book_pk);
    }

}
