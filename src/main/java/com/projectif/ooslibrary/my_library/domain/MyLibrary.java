package com.projectif.ooslibrary.my_library.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED
)
public class MyLibrary {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "my_library_pk")
    private Long myLibraryPk;
    @Column(length = 500)
    private String myLibraryName;
}
