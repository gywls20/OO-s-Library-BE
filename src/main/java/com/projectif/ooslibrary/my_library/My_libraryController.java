package com.projectif.ooslibrary.my_library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class My_libraryController {
    private final My_libraryService myLibraryService;

    @Autowired
    public My_libraryController(My_libraryService myLibraryService) {
        this.myLibraryService = myLibraryService;
    }

    @PutMapping("/bookPlus")
    public ResponseEntity<String> saveBookToLibrary(@RequestParam Long bookPk,
                                                    @RequestParam Long myLibraryPk) {
        myLibraryService.saveBookPlus(bookPk, myLibraryPk);
        return ResponseEntity.ok("Book saved to library successfully");
    }
}
