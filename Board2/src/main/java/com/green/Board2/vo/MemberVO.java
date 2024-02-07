package com.green.Board2.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberVO {
    private String memberId;
    private String memberPw;
    private String memberName;
    private String gender;
    private String memberEmail;
    private String isAdmin;
//<result column="MEMBER_ID" property="memberId"/>
//        <result column="MEMBER_PW" property="memberPw"/>
//        <result column="MEMBER_NAME" property="memberName"/>
//        <result column="GENDER" property="gender"/>
//        <result column="MEMBER_EMAIL" property="memberEmail"/>
//        <result column="IS_ADMIN" property="isAdmin"/>
}
