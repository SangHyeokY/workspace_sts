package com.green.shop.buy.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class BuyVO {
    private int buyCode;
    private String memberId;
    private int buyPrice;
    private String buyDate;
    private List<BuyDetailVO> buyDetailList;
}
