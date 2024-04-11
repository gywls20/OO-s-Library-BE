package com.projectif.ooslibrary.my_library.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MyLibrary {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "my_library_pk")
    private Long myLibraryPk;
    @Column(length = 500)
    private String myLibraryName;

    @Builder
    public MyLibrary(String myLibraryName) {
        this.myLibraryName = myLibraryName;
    }
}
