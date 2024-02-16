package com.green.shop.study.fetch.controller;

public class SamsungPrinter implements MyPrinter{
    public void grayPrint(){
        System.out.println("삼성프린터 - 흑백출력");
    }
    public void colorPrint(){
        System.out.println("삼성프린터 - 컬러출력");
    }
}
