package com.projectif.ooslibrary.book;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class BookTextDTO {
    private String bookTextPath;
    private String coverImagePath;

    public BookTextDTO(String bookTextPath, String coverImagePath){
        this.bookTextPath = bookTextPath;
        this.coverImagePath = coverImagePath;
    }
}
