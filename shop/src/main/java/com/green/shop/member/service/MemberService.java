package com.green.shop.member.service;

import com.green.shop.member.vo.MemberVO;

public interface MemberService {

    //회원 가입
    void join(MemberVO memberVO);

    //로그인
    MemberVO login(MemberVO memberVO);

    //내정보 보기
    MemberVO selectMyInfo(String memberId);

    //내정보 업데이트
    void updateRoll(MemberVO memberVO);
}
