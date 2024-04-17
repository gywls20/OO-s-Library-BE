package com.projectif.ooslibrary.my_library.repository;

import com.projectif.ooslibrary.book.domain.Book;
import com.projectif.ooslibrary.my_library.domain.BookPlus;
import com.projectif.ooslibrary.my_library.domain.MyLibrary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookPlusRepository extends JpaRepository<BookPlus, Long> {
    boolean existsByBookAndMyLibrary(Book book, MyLibrary myLibrary);
}
