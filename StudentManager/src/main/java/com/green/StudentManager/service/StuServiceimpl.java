package com.green.StudentManager.service;

import com.green.StudentManager.vo.StuVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("stuService")
public class StuServiceimpl implements StuService{
    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public int insertStu(StuVO stuVO) {
        int result = sqlSession.insert("insertStu", stuVO);
        return result;
    }

    @Override
    public List<StuVO> selectStuList() {
        return sqlSession.selectList("selectStuList");
    }

    //내가 만들어 넣은거 / 수업 중 수정
    @Override
    public StuVO selectStu(int stuNo) {
        return sqlSession.selectOne("selectStu", stuNo);
    }

    //학생 삭제
    @Override
    public int deleteStu(int stuNo) {
        return sqlSession.delete("deleteStu", stuNo);
    }


}
