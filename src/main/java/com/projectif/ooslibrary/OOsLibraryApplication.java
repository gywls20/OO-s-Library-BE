package com.projectif.ooslibrary;

import com.projectif.ooslibrary.admin.config.MyBatisConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@Import(MyBatisConfig.class)
@MapperScan(basePackages = "com.projectif.ooslibrary.mapper")
public class OOsLibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(OOsLibraryApplication.class, args);
    }

}
