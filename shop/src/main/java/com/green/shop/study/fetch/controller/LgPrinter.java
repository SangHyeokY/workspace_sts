package com.green.shop.study.fetch.controller;

public class LgPrinter implements MyPrinter{
    public void grayPrint(){
        System.out.println("엘지프린터 - 흑백출력");
    }
    public void colorPrint(){
        System.out.println("엘지프린터 - 컬러출력");
    }
}
