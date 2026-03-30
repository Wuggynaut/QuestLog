package com.arimattitoivonen.questlog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/index", "/"})
    public String frontPage() {
        return "index";
    }
}
