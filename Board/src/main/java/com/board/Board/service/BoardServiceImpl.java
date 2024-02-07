package com.board.Board.service;

import com.board.Board.vo.BoardVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("boardService")
public class BoardServiceImpl implements BoardService{

    @Autowired
    private SqlSessionTemplate sqlSession;


    //게시글 등록
    @Override
    public void insertBoard(BoardVO boardVO) {
        sqlSession.insert("boardMapper.insertBoard", boardVO);
    }

    //게시글 조회
    @Override
    public List<BoardVO> selectBoardList() {
       List<BoardVO> list=  sqlSession.selectList("boardMapper.selectBoardList");
        return list;
    }

    //게시글 보기
    @Override
    public BoardVO selectBoardDetail(int boardNum) {
       BoardVO result = sqlSession.selectOne("boardMapper.selectBoardDetail", boardNum);
        return result;
    }

    //게시물 삭제
    @Override
    public void deleteBoard(int boardNum) {
        sqlSession.delete("boardMapper.deleteBoard", boardNum);
    }

    //게시물 수정
    @Override
    public void updateBoard(BoardVO boardVO) {
        sqlSession.update("boardMapper.updateBoard", boardVO);
    }

    //조회수
    @Override
    public void viewCnt(int readCnt) {
    }
}
