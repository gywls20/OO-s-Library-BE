package com.projectif.ooslibrary;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model) {
        return "index/index";
    }

    @GetMapping("/resume")
    public String resume(Model model) {
        return "index/resume";
    }

    @GetMapping("/projects")
    public String projects(Model model) {
        return "index/projects";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        return "index/contact";
    }
}
