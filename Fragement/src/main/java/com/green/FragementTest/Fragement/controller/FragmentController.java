package com.green.FragementTest.Fragement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FragmentController {

    @GetMapping("/")
    public String main(){
        return "content/content_1";
    }

    @GetMapping("/content2")
    public String content2(){
        return "content/content_2";
    }
}
