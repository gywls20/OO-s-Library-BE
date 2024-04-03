package com.projectif.ooslibrary;

import com.projectif.ooslibrary.admin.config.MyBatisConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@Import(MyBatisConfig.class)
@EnableJpaAuditing
@SpringBootApplication
public class OOsLibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(OOsLibraryApplication.class, args);
    }

}
