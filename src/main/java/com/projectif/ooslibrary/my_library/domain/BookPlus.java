package com.projectif.ooslibrary.my_library.domain;

import com.projectif.ooslibrary.book.domain.Book;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BookPlus {
    @Id @Column(name="book_plus_pk")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookPlusPk;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_pk")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "my_library_pk")
    private MyLibrary myLibrary;

    @CreationTimestamp
    private LocalDateTime created_date;
    private boolean is_deleted;

}
