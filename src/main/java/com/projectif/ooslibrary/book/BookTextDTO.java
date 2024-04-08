package com.projectif.ooslibrary.book;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class BookTextDTO {
    private String bookTextPath;


    public BookTextDTO(String bookTextPath){
        this.bookTextPath = bookTextPath;
    }
}
