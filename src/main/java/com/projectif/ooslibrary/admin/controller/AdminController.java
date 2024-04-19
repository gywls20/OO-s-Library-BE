package com.projectif.ooslibrary.admin.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/adminPage")
    public String index() { //관리자페이지 폼
        return "pages/admin/adminPage"; //
    }


    @GetMapping("/editpost")
    public String editPost() { //게시글 관리 폼

        return "pages/admin/editPost";
    }

    @GetMapping("/reportlist")
    public String reportList() { // 신고게시글 폼

        return "pages/admin/reportList";
    }


}
