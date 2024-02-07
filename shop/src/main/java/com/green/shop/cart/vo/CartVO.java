package com.green.shop.cart.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class CartVO {
        private int itemCode;//
        private String memberId;//
        private int cartCnt;//
        private int cartCode; //
        private String cartDate;

        //쿼리. 정수 여러개 들어옴
        private List<Integer> cartCodeList;
}

