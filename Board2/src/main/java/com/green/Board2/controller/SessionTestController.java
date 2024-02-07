package com.green.Board2.controller;

import com.green.Board2.vo.MemberVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class SessionTestController {

    //이러면 주소가 localhost:8081/test/
    @GetMapping("/page1")
    public String page1(HttpSession session){

        //세션에 데이터 저장하기
        session.setAttribute("name", "java");
        session.setAttribute("age", 20);
        session.setAttribute("member", new MemberVO());
        //세션이 유지되는 최대 시간 설정 (초 단위)
        // 60초 * 30 = 30분
        session.setMaxInactiveInterval(60 * 30);

        return "page1";
    }

    @GetMapping("/page2")
    public String page2(HttpSession session){

        //세션에 담긴 데이터 확인
        String name = (String) session.getAttribute("name");
        int age = (int) session.getAttribute("age");
        MemberVO vo = (MemberVO) session.getAttribute("member");

        //세션 데이터 삭제 (2가지)
        //1.
        session.removeAttribute("age");

        //2. 모든 세션 데이터 지우기
        session.invalidate();

        return "page2";
    }

    @GetMapping("/page3")
    public String page3(){
        return "page3";
    }

}
