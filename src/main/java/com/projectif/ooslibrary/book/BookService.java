package com.projectif.ooslibrary.book;

import com.projectif.ooslibrary.book.domain.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    @Transactional
    public List<BookDTO> getBookList() {
        List<Book> books = bookRepository.findAll();
        List<BookDTO> bookDTOList = new ArrayList<>();

        for (Book book : books) {
            BookDTO bookDTO = BookDTO.builder()
                    .book_pk(book.getBook_pk())
                    .book_title(book.getBook_title())
                    .book_author(book.getBook_author())
                    .book_publisher(book.getBook_publisher())
                    .book_category(book.getBook_category())
                    .cover_image_path(book.getCover_image_path())
                    .book_content(book.getBook_content())
                    .book_publish_date(book.getBook_publish_date())
                    .book_price(book.getBook_price())
                    .book_isbn(book.getBook_isbn())
                    .aladin_link(book.getAladin_link())
                    .book_page(book.getBook_page())
                    .book_original_title(book.getBook_original_title())
                    .build();
            bookDTOList.add(bookDTO);
        }
        return bookDTOList;
    }

    @Transactional(readOnly = true)
    public BookTextDTO getBookPath(Long book_pk) {
        Optional<Book> optionalBook = bookRepository.findById(book_pk);
        return optionalBook.map(book -> new BookTextDTO(book.getBook_text_path())).orElse(null);
    }
}
