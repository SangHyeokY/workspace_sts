package com.green.Board2.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReplyVO {
    private int replyNum;
    private String content;
    private String createDate;
    private String writer;
    private int boardNum;
//
//    <result column="REPLY_NUM" property="replyNum"/>
//        <result column="CONTENT" property="content"/>
//        <result column="CREATE_DATE" property="createDate"/>
//        <result column="WRITER" property="writer"/>
//        <result column="BOARD_NUM" property="boardNum"/>
//
//


}
