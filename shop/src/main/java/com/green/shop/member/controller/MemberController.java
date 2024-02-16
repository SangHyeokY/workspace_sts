package com.green.shop.member.controller;

import com.green.shop.cart.vo.CartViewVO;
import com.green.shop.member.service.MemberService;
import com.green.shop.member.service.MemberServiceImpl;
import com.green.shop.member.vo.MemberVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Resource(name="memberService")
    private MemberServiceImpl memberService;

    //회원가입
    @PostMapping("/join")
    public String join(MemberVO memberVO){
        //문자열 치환 replace( , );

        // 연락처 세팅
        memberVO.setMemberTel(memberVO.getMemberTel().replace(",", "-"));

        //이메일 세팅
        memberVO.setMemberEmail(memberVO.getMemberEmail().replace(",", ""));

        //회원가입 쿼리를 실행
        memberService.join(memberVO);
        return "redirect:/item/list";
    }

    //로그인 화면으로
    @GetMapping("/loginForm")
    public String loginForm(){
        return "content/member/login";
    }

    //로그인 버튼 눌렀을때
    @PostMapping("/login")
    public String login(MemberVO memberVO, HttpSession session){
        //memberService : 정보조회
        //loginInfo 만들기
        MemberVO loginInfo = memberService.login(memberVO);
        //모든 정보가 들어와야 로그인
        if(loginInfo != null){
            //정보조회 결과 null 이면 X, session 에 있으면 loginInfo 에 들어감
            session.setAttribute("loginInfo", loginInfo);
        }
        return "content/member/login_result";
    }

    //로그아웃 하고 싶을때
    @GetMapping("/logoutForm")
    public String logoutForm(HttpSession session){
        session.removeAttribute("loginInfo");
        return "redirect:/item/list";
    }

    //비동기 로그인
    @ResponseBody
    @PostMapping("/loginFetch")
    public String loginFetch(MemberVO memberVO, HttpSession session) {
        MemberVO loginInfo = memberService.login(memberVO);
        //모든 정보가 들어와야 로그인
        if (loginInfo != null) {
            //정보조회 결과 null 이면 X, session 에 있으면 loginInfo 에 들어감
            session.setAttribute("loginInfo", loginInfo);
        }
        return loginInfo == null ? ""  : loginInfo.getMemberId();
    }

    //내 정보 보기
    @GetMapping("/myInfo")
    public String myInfo(@RequestParam(name="page", required = false, defaultValue = "myInfo")
                             String page, Model model, HttpSession session){

        //로그인 정보 조회
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");

        //정보 보내기
        MemberVO myInfoList = memberService.selectMyInfo(loginInfo.getMemberId());
        model.addAttribute("myInfoList", myInfoList);

        model.addAttribute("page", page);
        return "content/member/my_Info";
    }




}
