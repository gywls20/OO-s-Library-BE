package com.projectif.ooslibrary.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public List<BookDTO> bookList(){
        //System.out.println(" 연결 성공 ");
        return bookService.getBookList();
    }

    //@GetMapping("/books/Text")
    @GetMapping("/books/{book_pk}")
    public BookTextDTO getBookText(@PathVariable Long book_pk) {
        return bookService.getBookPath(book_pk);
    }

//    @GetMapping("/readFile")
//    public String readFileFromURL() {
//        // 외부 URL에서 파일을 읽기 위해 RestTemplate 사용
//        RestTemplate restTemplate = new RestTemplate();
//
//        // 외부 URL에 HTTP GET 요청을 보내 파일 내용을 가져옴
//        String fileContent = restTemplate.getForObject("https://kr.object.ncloudstorage.com/library-ebook/%ED%98%84%EC%A7%84%EA%B1%B4-%EC%9A%B4%EC%88%98_%EC%A2%8B%EC%9D%80_%EB%82%A0%2BB3356-%EA%B0%9C.json", String.class);
//
//        // 가져온 파일 내용을 반환
//        return fileContent;
//    }
}
