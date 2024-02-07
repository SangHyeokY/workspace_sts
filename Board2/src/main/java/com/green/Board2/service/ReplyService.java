package com.green.Board2.service;

import com.green.Board2.vo.ReplyVO;

import java.util.List;

public interface ReplyService {

    //댓글 작성
    void insertReply(ReplyVO replyVO);

    //댓글 보여주기
    List<ReplyVO> selectReplyList(int boardNum);




}
