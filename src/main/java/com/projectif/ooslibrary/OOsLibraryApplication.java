package com.projectif.ooslibrary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class OOsLibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(OOsLibraryApplication.class, args);
    }

}
