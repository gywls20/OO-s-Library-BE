package com.projectif.ooslibrary.book;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class BookTextDTO {
    private String book_text_path;


    public BookTextDTO(String book_text_path){
        this.book_text_path = book_text_path;
    }
}
