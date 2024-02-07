package com.board.Board.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
//일단 작업해둠
@Getter
@Setter
@ToString
public class BoardVO {
    private int boardNum;
    private String boardTitle;
    private String boardContent;
    private String writer;
    private String createDate;
    private int readCnt;
}
