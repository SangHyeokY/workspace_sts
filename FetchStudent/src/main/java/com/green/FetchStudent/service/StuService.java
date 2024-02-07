package com.green.FetchStudent.service;

import com.green.FetchStudent.vo.ClassVO;
import com.green.FetchStudent.vo.ScoreVO;
import com.green.FetchStudent.vo.StuVO;

import java.util.List;

public interface StuService {

    //학생 목록 조회
    List<StuVO> selectStuList(StuVO stuVO);

    //학급 목록 조회
    List<ClassVO> selectClassList();

    //클릭후 학생 정보 조회
    StuVO selectStuDetail(int stuNUm);

    //학생 점수 조회
    ScoreVO selectScoreInfo(int stuNUm);

    //점수 등록
    void insertScore(ScoreVO scoreVO);
}
