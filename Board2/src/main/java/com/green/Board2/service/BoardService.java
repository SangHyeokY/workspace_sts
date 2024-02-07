package com.green.Board2.service;

import com.green.Board2.vo.BoardVO;
import com.green.Board2.vo.MemberVO;
import com.green.Board2.vo.SearchVO;

import java.util.List;

public interface BoardService {

    //게시글 등록
    void insertBoard(BoardVO boardVO);

    //게시글 목록 조회 (리스트 쫘악)
    List<BoardVO> selectBoardList(SearchVO searchVO);

    //게시글 상세
    BoardVO selectBoardDetail(int boardNum);

    //게시글 삭제 (번호만 조회하고 삭제하면 됨)
    void deleteBoard(int boardNum);

    //수정 (수정할거 다 불러와야 하므로 BoardVO)
    void updateBoard(BoardVO boardVO);

    //조회수 증가 (얘도 번호만 조회하면 됨)
    void updateReadCnt(int boardNum);

    //게시글 수 조회
    int selectBoardCnt();


}
