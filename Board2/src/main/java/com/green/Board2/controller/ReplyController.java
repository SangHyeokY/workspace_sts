package com.green.Board2.controller;

import com.green.Board2.service.ReplyService;
import com.green.Board2.vo.MemberVO;
import com.green.Board2.vo.ReplyVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.Writer;

@Controller
@RequestMapping("/reply")
public class ReplyController {

    @Resource(name="replyService")
    private ReplyService replyService;

    //댓글 등록하기!!
    @PostMapping("/regReply")
    public String regReply(ReplyVO replyVO, HttpSession session){
        //writer 가 null 이 생긴다.
        //loginInfo 에서 데이터를 가져오기
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");
        replyVO.setWriter(loginInfo.getMemberId());
        //댓글 등록
        replyService.insertReply(replyVO);
        return "redirect:/board/boardDetail?boardNum=" + replyVO.getBoardNum();
    }

}
