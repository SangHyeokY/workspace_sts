package com.green.FetchStudent.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ScoreVO {
    private int scoreNum;
    private int korScore;
    private int engScore;
    private int mathScore;
    private int stuNum;

//  <result column="SCORE_NUM" property="scoreNum"/>
//        <result column="KOR_SCORE" property="korScore"/>
//        <result column="ENG_SCORE" property="engScore"/>
//        <result column="MATH_SCORE" property="mathScore"/>
//        <result column="STU_NUM" property="stuNum"/>

}
