package com.green.Board2.controller;

import com.green.Board2.service.MemberServiceImpl;
import com.green.Board2.vo.MemberVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {

    //Impl 이랑 이어주는거
    @Resource(name="memberService")
    private MemberServiceImpl memberService;

    //@@ 로그인 페이지로 이동
    @GetMapping("/home")
    public String loginForm(){
        return "login";
    }

    //회원가입 페이지로 이동
    @GetMapping("/joinForm")
    public String joinForm(){
        return "join";
    }

    //진짜 회원가입
    @PostMapping("/join")
    public String join(MemberVO memberVO){
        //회원가입
        memberService.join(memberVO);

        //이거 잘못됨
        return "redirect:/member/home";
    }

    //게시글 보러가기
    @GetMapping("/listForm")
    public String listForm(){
        return "board_list";
    }

    //로그인 구현
    @PostMapping("/login")
    public String login(MemberVO memberVO, HttpSession session){
        //loginInfo -> member-mapper
        MemberVO loginInfo = memberService.login(memberVO);

        //로그인 정보가 조회 되면,
        if(loginInfo != null){
            //세션에 로그인 정보를 저장
            session.setAttribute("loginInfo", loginInfo);
        }
        return "login_result";
    }

    //로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("loginInfo");
        return "redirect:/board/listForm";
    }

}
