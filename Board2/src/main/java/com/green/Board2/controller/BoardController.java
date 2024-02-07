package com.green.Board2.controller;


import com.green.Board2.service.BoardServiceImpl;
import com.green.Board2.service.ReplyService;
import com.green.Board2.service.ReplyServiceImpl;
import com.green.Board2.vo.BoardVO;
import com.green.Board2.vo.MemberVO;
import com.green.Board2.vo.ReplyVO;
import com.green.Board2.vo.SearchVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Resource(name = "boardService")
    private BoardServiceImpl boardService;

    @Resource(name = "replyService")
    private ReplyServiceImpl replyService;

    //목록조회, 글쓰기, 상세조회, 수정, 삭제

    //게시글 목록 페이지
    @RequestMapping("/listForm")
    //Request 는 Get, Post 둘 다 받아짐
    public String boardList(Model model, SearchVO searchVO){
        //html 에서 못불러오던게 여기 문제임
//        List<BoardVO> list = boardService.selectBoardList();
//        model.addAttribute("boardList", list);

        //전체 페이지 데이터 수를 먼저 파악한다
        int totalDataCnt =  boardService.selectBoardCnt();
        searchVO.setTotalDataCnt(totalDataCnt);

        //페이지 정보 세팅
        searchVO.setPageInfo();

        model.addAttribute("boardList", boardService.selectBoardList(searchVO));
        //던지는 데이터의 이름 : boardList
        return "board_list";
    }

    //글쓰기 페이지로 이동
//    @PostMapping("/boardWriteForm")
//    public String boardWriteForm(BoardVO boardVO){
//        return "board_write";
//    }

    //글쓰기 페이지로 이동2
    @GetMapping("/writeForm")
    public String writeForm(){
        return "board_write";
    }


    //게시글 작성
    //등록하기...
    @PostMapping("/boardWrite")
    public String boardWrite(BoardVO boardVO, HttpSession session){
        //세션에 저장된 로그인한 유저의 아이디를 조회
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");

        //boardVO에 작성자 데이터 저장
        boardVO.setWriter(loginInfo.getMemberId());

        //게시글 등록, insert 하기
        boardService.insertBoard(boardVO);

        return "redirect:/board/listForm";
    }

    //게시글 상세보기
    @GetMapping("/boardDetail")
    private String boardDetail(@RequestParam(name = "boardNum") int boardNum, Model model){

        //조회수 증가
        boardService.updateReadCnt(boardNum);

        BoardVO vo = boardService.selectBoardDetail(boardNum);
        model.addAttribute("board",vo);

        //댓글 보기
        List<ReplyVO> replyList = replyService.selectReplyList(boardNum);
        model.addAttribute("replyList", replyList);

        return "board_detail";
    }



    //게시글 수정 -- 마저하기
    @GetMapping("/updateBoardForm")
    public String updateBoardForm(BoardVO boardVO, Model model){

        //수정하고자 하는 게시글의 데이터를 조회 + html 전달
        BoardVO boardList = boardService.selectBoardDetail(boardVO.getBoardNum());
        model.addAttribute("boardList", boardList);

        //수정하기
        boardService.updateBoard(boardVO);

        return "board_rewrite";




    }

    //게시글 삭제
    @GetMapping("/delete")
    public String delete(@RequestParam(name = "boardNum") int boardNum){
        boardService.deleteBoard(boardNum);
        return "redirect:/board/listForm";
    }




}
