package com.green.Second.controller;

import com.green.Second.vo.ResumeVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ResumeController {

    //서버 열고 실행하는 주소 정함
    @GetMapping("/r1")
    public String first() {
        return "resume_1";
    }

    //페이지가 넘거가는데 post 를 받아야하므로 PostMapping
    @PostMapping("/r2")
    public String second(@RequestParam(name = "name") String name,
                         @RequestParam(name = "tel") String tel, Model model) {
        System.out.println("name = " + name);
        System.out.println("tel = " + tel);
        model.addAttribute("name", name);
        model.addAttribute("tel", tel);
        return "resume_2";
    }

    //이렇게 만들기에는 자료가 너무 많아서
    //따로 자료를 다 받을 수 있는 클래스 vo를 만듦

    @PostMapping("/r3")
    public String third(ResumeVO resumeVO){
        System.out.println(resumeVO);
        return "resume_3";
    }

}