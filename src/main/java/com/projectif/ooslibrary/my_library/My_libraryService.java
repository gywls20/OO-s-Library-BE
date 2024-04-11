package com.projectif.ooslibrary.my_library;

import com.projectif.ooslibrary.my_library.domain.BookPlus;
import com.projectif.ooslibrary.my_library.domain.MyLibrary;
import com.projectif.ooslibrary.my_library.repository.BookPlusRepository;
import com.projectif.ooslibrary.my_library.repository.MyLibraryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class My_libraryService {

    private final BookPlusRepository bookPlusRepository;
    private final MyLibraryRepository myLibraryRepository;

    @Transactional //빌더 패턴 사용안함,,
    public void saveBookPlus(Long bookPk, Long myLibraryPk) {
        BookPlus bookPlus = new BookPlus();
        bookPlus.setBookPk(bookPk);
        MyLibrary findMyLibrary = myLibraryRepository.findById(myLibraryPk)
                .orElseThrow(() -> new RuntimeException("내 서재가 존재하지 않습니다"));
        bookPlus.setMyLibrary(findMyLibrary);

        bookPlusRepository.save(bookPlus);
    }


}
