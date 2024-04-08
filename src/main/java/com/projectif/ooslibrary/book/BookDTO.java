package com.projectif.ooslibrary.book;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private Long bookPk; //
    private String bookTitle;//
    private String bookAuthor;//
    private String bookPublisher;//

    private String bookPublishDate;
    private int bookPrice;
    private String bookIsbn;
    private String bookCategory;//
    private String coverImagePath;//
    private String bookTextPath;
    private String aladinLink;
    private int bookPage;
    private String bookContent;//
    private String bookOriginalTitle;

    @Builder
    public BookDTO(Long bookPk, String bookTitle, String bookAuthor, String bookPublisher, String bookPublishDate,
                int bookPrice, String bookIsbn, String bookCategory, String coverImagePath, String aladinLink,
                int bookPage, String bookContent, String bookOriginalTitle) {

        this.bookPk = bookPk;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookPublisher = bookPublisher;
        this.bookPublishDate = bookPublishDate;
        this.bookPrice = bookPrice;
        this.bookIsbn = bookIsbn;
        this.bookCategory = bookCategory;
        this.coverImagePath = coverImagePath;
        this.aladinLink = aladinLink;
        this.bookPage = bookPage;
        this.bookContent = bookContent;
        this.bookOriginalTitle = bookOriginalTitle;
        //this.book_text_path = book_text_path;
    }

}
