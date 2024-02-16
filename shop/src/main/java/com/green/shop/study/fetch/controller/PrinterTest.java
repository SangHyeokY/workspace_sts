package com.green.shop.study.fetch.controller;

import com.green.shop.item.service.ItemService;
import com.green.shop.item.service.ItemServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class PrinterTest {
    public void setPrinter() {

        Map<String, String> map1 = new HashMap<>();
        HashMap<String, String> map2 = new HashMap<>();

        ItemService i1 = new ItemServiceImpl();
        ItemServiceImpl i2 = new ItemServiceImpl();

        //인터페이스를 구현한 클래스의 생성자를 호출해서
        //인터페이스의 객체를 생성할 수 있다.
        MyPrinter print = new LgPrinter();
        MyPrinter print3 = new SamsungPrinter();
        LgPrinter print2 = new LgPrinter();
        print.colorPrint();
        print.grayPrint();

    }
}
