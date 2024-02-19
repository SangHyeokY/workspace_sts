package com.green.shop.admin.controller;

import com.green.shop.item.service.ItemService;
import com.green.shop.item.vo.CategoryVO;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//리액트 연습용 컨트롤러
@RestController //알아서 전부 비동기로 바꿔줌
@RequestMapping("/rest")
public class ReactController {

    @Resource(name = "itemService")
    private ItemService itemService;

    @GetMapping("/test1")
    public String test1(){
        return "test.html";
    }

    @GetMapping("/test2")
    public List<CategoryVO> test2(){
        return itemService.selectCategoryList();
    }
}
