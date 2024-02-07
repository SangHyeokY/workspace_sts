package com.green.Board2.service;

import com.green.Board2.vo.BoardVO;
import com.green.Board2.vo.SearchVO;
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


    //게시글 목록 조회
    @Override
    public List<BoardVO> selectBoardList(SearchVO searchVO) {
        //다 불러와야 하므로 List
        List<BoardVO> list = sqlSession.selectList("boardMapper.selectBoardList", searchVO);
        return list;
    }

    //게시글 조회/보기 @@
    @Override
    public BoardVO selectBoardDetail(int boardNum) {
        //데이터 하나만 받으므로 one
        BoardVO result = sqlSession.selectOne("boardMapper.selectBoardDetail", boardNum);
        return result;
    }

    //게시글 삭제
    @Override
    public void deleteBoard(int boardNum) {
        sqlSession.delete("boardMapper.deleteBoard", boardNum);
    }

    //게시글 수정
    @Override
    public void updateBoard(BoardVO boardVO) {
        sqlSession.update("boardMapper.updateBoard", boardVO);

    }

    //조회수 증가
    @Override
    public void updateReadCnt(int boardNum) {
        sqlSession.update("boardMapper.updateReadCnt", boardNum);
    }


    //게시글 수 조회
    @Override
    public int selectBoardCnt() {
        return sqlSession.selectOne("boardMapper.selectBoardCnt");
    }


}
