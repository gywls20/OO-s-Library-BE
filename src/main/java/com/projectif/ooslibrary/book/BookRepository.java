package com.projectif.ooslibrary.book;

import com.projectif.ooslibrary.book.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByBookCategoryOrderByBookTitleAsc(String category);
    List<Book> findByBookCategoryOrderByBookTitleDesc(String category);
    List<Book> findAllByOrderByBookTitleAsc();
    List<Book> findAllByOrderByBookTitleDesc();
}
