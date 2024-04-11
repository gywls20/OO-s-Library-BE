package com.projectif.ooslibrary.my_library.repository;

import com.projectif.ooslibrary.my_library.domain.MyLibrary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyLibraryRepository extends JpaRepository<MyLibrary, Long> {

}
