package com.green.FetchStudent.service;

import com.green.FetchStudent.vo.ClassVO;
import com.green.FetchStudent.vo.ScoreVO;
import com.green.FetchStudent.vo.StuVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("stuService")
public class StuServiceImpl implements StuService{

    @Autowired
    private SqlSessionTemplate sqlSession;

    //번호 선택시 출력. 학생 조회
    @Override
    public List<StuVO> selectStuList(StuVO stuVO) {
        return sqlSession.selectList("stuMapper.selectStuList", stuVO);
    }

    //학급 조회
    @Override
    public List<ClassVO> selectClassList() {
        return sqlSession.selectList("stuMapper.selectClassList");
    }

    //stuNum 만 조회하므로 selectOne
    @Override
    public StuVO selectStuDetail(int stuNUm) {
        return sqlSession.selectOne("stuMapper.selectStuDetail", stuNUm);
    }

    //stuNum 만 조회하므로 selectOne
    @Override
    public ScoreVO selectScoreInfo(int stuNUm) {
        return sqlSession.selectOne("stuMapper.selectScoreInfo", stuNUm);
    }

    //점수 넣기
    @Override
    public void insertScore(ScoreVO scoreVO) {
        sqlSession.insert("stuMapper.insertScore", scoreVO);
    }


}



