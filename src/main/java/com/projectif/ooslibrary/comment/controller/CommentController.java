package com.projectif.ooslibrary.comment.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class CommentController {

    @GetMapping("/comment")
    public String commentTest() {
        return "comment test";
    }
}
