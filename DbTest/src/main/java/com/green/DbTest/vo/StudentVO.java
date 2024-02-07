package com.green.DbTest.vo;

// STUDENT 테이블과 매칭되는 클래스

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StudentVO {
     private int stuNo;
     public  String stuName;
     public int score;
     public String addr;
}
