package com.green.shop.cart.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class CartViewVO {
    private int itemCode;
    private int cartCnt;
    private int cartCode;
    private String memberId;
    private String cartDate;
    private String itemName;
    private int itemPrice;
    private String itemIntro;
    private String memberName;
    private String memberTel;
    private String attachedFileName;
    private String originFileName;
    private int imgCode;
    private int cateCode;
    private String cateName;
    private String isMain;
    private int totalPrice;
    private String address;
}
