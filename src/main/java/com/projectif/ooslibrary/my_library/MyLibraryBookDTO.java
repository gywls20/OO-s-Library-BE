package com.projectif.ooslibrary.my_library;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
public class MyLibraryBookDTO {

    private Long bookPlusPk;
    private Long bookPk;
    private String bookTitle;
    private String bookAuthor;
    private String bookPublisher;
    private String bookPublishDate;
    private int bookPrice;
    private String bookCategory;
    private String coverImagePath;
    private String bookContent;

}
