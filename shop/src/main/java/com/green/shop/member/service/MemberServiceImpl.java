package com.green.shop.member.service;

import com.green.shop.member.vo.MemberVO;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("memberService")
public class MemberServiceImpl implements MemberService{

    @Autowired
    private SqlSessionTemplate sqlSession;

    //회원 가입
    @Override
    public void join(MemberVO memberVO) {
        sqlSession.insert("memberMapper.join", memberVO);
    }

    //로그인
    @Override
    public MemberVO login(MemberVO memberVO) {
        return sqlSession.selectOne("memberMapper.login", memberVO);
    }

    //내정보 보기
    @Override
    public MemberVO selectMyInfo(String memberId) {
        return sqlSession.selectOne("memberMapper.selectMyInfo", memberId);
    }

    //내정보 권한 업데이트
    @Override
    public void updateRoll(MemberVO memberVO) {
        sqlSession.update("memberMapper.updateRoll", memberVO);
    }




}
