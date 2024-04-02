package com.projectif.ooslibrary.book.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Book {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_pk")
    private Long book_pk;
    private String book_title;
    private String book_author;
    private String book_publisher;
    private String book_publish_date;
    private int book_price;
    private String book_isbn;
    private String book_category;
    private String cover_image_path;
    private String book_text_path;
    private String aladin_link;
    private int book_page;
    private String book_content;
    private String book_original_title;
    @CreationTimestamp
    private LocalDateTime created_date;
    private boolean is_deleted;




}
