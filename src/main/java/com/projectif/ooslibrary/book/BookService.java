package com.projectif.ooslibrary.book;

import com.projectif.ooslibrary.book.domain.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

public List<BookDTO> getAllBooks(String sortOrder) {
    List<Book> books;
    if ("desc".equals(sortOrder)) {
        books = bookRepository.findAllByOrderByBookTitleDesc();
    } else {
        books = bookRepository.findAllByOrderByBookTitleAsc();
    }
    return books.stream().map(this::convertToDTO).collect(Collectors.toList());
}
    public List<BookDTO> getBooksByCategory(String category, String sortOrder) {
        List<Book> books;
        if ("desc".equals(sortOrder)) {
            books = bookRepository.findByBookCategoryOrderByBookTitleDesc(category);
        } else {
            books = bookRepository.findByBookCategoryOrderByBookTitleAsc(category);
        }
        return books.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private BookDTO convertToDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setBookPk(book.getBookPk());
        bookDTO.setBookTitle(book.getBookTitle());
        bookDTO.setBookAuthor(book.getBookAuthor());
        bookDTO.setBookPublisher(book.getBookPublisher());
        bookDTO.setBookCategory(book.getBookCategory());
        bookDTO.setCoverImagePath(book.getCoverImagePath());
        bookDTO.setBookContent(book.getBookContent());
        bookDTO.setBookPublishDate(book.getBookPublishDate());
        bookDTO.setBookPrice(book.getBookPrice());
        bookDTO.setBookIsbn(book.getBookIsbn());
        bookDTO.setAladinLink(book.getAladinLink());
        bookDTO.setBookPage(book.getBookPage());
        bookDTO.setBookOriginalTitle(book.getBookOriginalTitle());
        return bookDTO;
    }

    public BookTextDTO getBookPath(Long book_pk) {
        Optional<Book> optionalBook = bookRepository.findById(book_pk);
        return optionalBook.map(book -> new BookTextDTO(book.getBookTextPath()))
                .orElseThrow(() -> new RuntimeException("해당하는 책을 찾을 수 없습니다."));
    }


    public List<BookDTO> getLibraryBooks(String category){
        ArrayList<Book> books = new ArrayList<>();


        return getAllBooks("asc");
    }
}
