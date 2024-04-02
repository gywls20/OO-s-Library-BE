package com.projectif.ooslibrary.book;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private Long book_pk; //
    private String book_title;//
    private String book_author;//
    private String book_publisher;//

    private String book_publish_date;
    private int book_price;
    private String book_isbn;
    private String book_category;//
    private String cover_image_path;//
    private String book_text_path;
    private String aladin_link;
    private int book_page;
    private String book_content;//
    private String book_original_title;

    @Builder
    public BookDTO(Long book_pk, String book_title, String book_author, String book_publisher, String book_publish_date,
                int book_price, String book_isbn, String book_category, String cover_image_path, String aladin_link,
                int book_page, String book_content, String book_original_title) {

        this.book_pk = book_pk;
        this.book_title = book_title;
        this.book_author = book_author;
        this.book_publisher = book_publisher;
        this.book_publish_date = book_publish_date;
        this.book_price = book_price;
        this.book_isbn = book_isbn;
        this.book_category = book_category;
        this.cover_image_path = cover_image_path;
        this.aladin_link = aladin_link;
        this.book_page = book_page;
        this.book_content = book_content;
        this.book_original_title = book_original_title;
        //this.book_text_path = book_text_path;
    }

//    @Builder
//    public void BookTextDTO(String book_text_path){
//        this.book_text_path = book_text_path;
//    }

}
