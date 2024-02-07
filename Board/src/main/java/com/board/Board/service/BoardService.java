package com.board.Board.service;

import com.board.Board.vo.BoardVO;

import java.util.List;

public interface BoardService {

    //게시물 등록
   void insertBoard(BoardVO boardVO);

   //게시물 이동
    // () : 빈 공간 있어도 된다.
   List<BoardVO> selectBoardList();

   //게시물 조회
    BoardVO selectBoardDetail(int boardNum);

    //삭제
    void deleteBoard(int boardNum);

    //게시물 정보수정
    void updateBoard(BoardVO boardVO);

    //조회수
    void viewCnt(int readCnt);
}
