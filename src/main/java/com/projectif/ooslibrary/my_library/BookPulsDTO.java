package com.projectif.ooslibrary.my_library;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookPulsDTO {
    private Long bookPlusPk;
    private Long bookPk;
    private Long myLibraryPk;
    private LocalDateTime created_date;
    private boolean is_deleted;

}
