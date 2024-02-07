package com.green.shop.member.vo;

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
    private String memberTel;
    private String addrDetail;
    private String postCode;
    private String memberAddr;
    private String joinDate;
    private String memberRoll;

//    MEMBER_ID VARCHAR(20) PRIMARY KEY
//	, MEMBER_PW VARCHAR(20) NOT NULL
//	, MEMBER_NAME VARCHAR(20) NOT NULL
//	, GENDER VARCHAR(10) NOT NULL -- 남자 male, 여자 female
//	, MEMBER_EMAIL VARCHAR(50) NOT NULL UNIQUE
//	, MEMBER_TEL VARCHAR(20) -- 010-1111-2222
//            , MEMBER_ADDR VARCHAR(50)
//	, ADDR_DETAIL VARCHAR(50)
//	, POST_CODE VARCHAR(10) -- 13011
//            , JOIN_DATE DATETIME DEFAULT CURRENT_TIMESTAMP
//	, MEMBER_ROLL VARCHAR(20) DEFAULT 'USER'-- 권한 USER, ADMIN


}
