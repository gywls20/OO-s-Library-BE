package com.projectif.ooslibrary.book.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@ToString
public class Book {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_pk")
    private Long bookPk;
    @Column(name = "book_title")
    private String bookTitle;
    @Column(name = "book_author")
    private String bookAuthor;
    @Column(name = "book_publisher")
    private String bookPublisher;
    @Column(name = "book_publish_date")
    private String bookPublishDate;
    @Column(name = "book_price")
    private int bookPrice;
    @Column(name = "book_isbn")
    private String bookIsbn;
    @Column(name = "book_category")
    private String bookCategory;
    @Column(name = "cover_image_path")
    private String coverImagePath;
    @Column(name = "book_text_path")
    private String bookTextPath;
    @Column(name = "aladin_link")
    private String aladinLink;
    @Column(name = "book_page")
    private int bookPage;
    @Column(name = "book_content")
    private String bookContent;
    @Column(name = "book_original_title")
    private String bookOriginalTitle;
    @CreationTimestamp
    private LocalDateTime created_date;
    private boolean is_deleted;




}
