package com.board.Board.controller;

import com.board.Board.service.BoardService;
import com.board.Board.service.BoardServiceImpl;
import com.board.Board.vo.BoardVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BoardController {

    @Resource(name="boardService")
    private BoardServiceImpl boardService;

    //게시물 목록페이지로 이동
    @GetMapping("/")
    public String boardList(Model model){
        //*****목록데이터 조회 후 HTML 전달*****
        List<BoardVO> boardList = boardService.selectBoardList();
        model.addAttribute("boardList", boardList);
        //데이터조회 가능
        //System.out.println(boardList.size());
        return "board_list";
    }

    //@@@@@글쓰기 페이지로 이동@@@@@
    @GetMapping("/boardWriteForm")
    public String boardWriteForm(BoardVO boardVO){
        return "board_write_form";
    }

    //@@@@@글 등록@@@@@
    @PostMapping("/boardWrite")
    public String boardWrite(BoardVO boardVO){
        //게시글 insert
        boardService.insertBoard(boardVO);
        return "redirect:/";
    }

    //게시글 상세보기 페이지로 이동
    @GetMapping("/boardDetail")
    private String boardDetail(BoardVO boardVO, Model model){
        //게시글 상세 정보 조회 + html.에 전달
        BoardVO board = boardService.selectBoardDetail(boardVO.getBoardNum());
        model.addAttribute("board", board);

        //해당 게시글에서 조회수 +1하기???
        boardService.viewCnt(boardVO.getReadCnt());

        return "board_detail";
    }

    //게시글 수정페이지로 이동
    @GetMapping("/updateBoardForm")
    public String updateBoardForm(BoardVO boardVO, Model model){
        //수정하고자 하는 게시글의 데이터를 조회 + html 전달
        BoardVO board = boardService.selectBoardDetail(boardVO.getBoardNum());
        model.addAttribute("board", board);
        return "update_form";
    }

    //게시글 삭제
    @GetMapping("/deleteBoard")
    private String deleteBoard(BoardVO boardVO){
        boardService.deleteBoard(boardVO.getBoardNum());
        return "redirect:/";
    }

    //게시글 수정입력
    @PostMapping("/updateBoard")
    private String updateBoard(BoardVO boardVO) {
        //게시글 수정
        boardService.updateBoard(boardVO);
        //불러온 글 + 새로쓴 글
        return "redirect:/boardDetail?boardNum=" + boardVO.getBoardNum();
    }
}
