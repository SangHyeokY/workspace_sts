package com.green.shop.study.fetch.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.shop.member.vo.MapDataVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/fetch")
public class FetchController {

    @GetMapping("/main")
    public String main(){
        return "test/fetch/main";
    }

    //넘어오는 데이터를 받을 때 사용하는 어노테이션 2종류
    //@RequestParam : url에 데이터가 함께 넘어올 때 사용
    //ex) localhost:8081/aaa?a=b
    //ex) form 태그를 사용
    //@RequestBody : url이 아닌 body 영역에 데이터가 담겨서 올 때 사용

    //VO객체로 데이터 전달받기
    @ResponseBody
    @PostMapping("/fetch1")
    public void fetch1(@RequestBody MemberVO memberVO){
        System.out.println("fetch1 메소드 실행");
        System.out.println(memberVO);
    }

    //Map을 사용해서 데이터 전달받기
    @ResponseBody
    @PostMapping("/fetch2")
    public void fetch2(@RequestBody HashMap<String, String> data){
        System.out.println("fetch2 메소드 실행");
        System.out.println(data);
        System.out.println(data.get("name"));
        System.out.println(data.get("id"));
        System.out.println(data.get("age"));
    }

    //배열 데이터를 list로 받기
    //Javascript에서 배열이 넘어오면 ArrayList로 받을 수 있다
    @ResponseBody
    @PostMapping("/fetch3")
    public void fetch3(@RequestBody ArrayList<MemberVO> list){
        System.out.println("fetch3 메소드 실행");
        System.out.println(list);
    }

    //복잡한 데이터 전달받기
    @ResponseBody
    @PostMapping("/fetch4")
    public void fetch4(@RequestBody HashMap<String, Object> map){
        System.out.println("fetch4 메소드 실행");
        System.out.println(map);
        //1. cartCode
        int cartCode = (int)map.get("cartCode"); //int로 형변환
        System.out.println(cartCode);
        //2. memberId
        HashMap<String, String> memberInfo = (HashMap<String, String>) map.get("memberInfo"); //map으로 형변환
        String memberId = memberInfo.get("memberId");
        System.out.println(memberId);

        //두번째 이미지의 imgCode 출력
        //fetch4의 map > itemInfo > imgList
        Map<String, Object> itemInfo = (Map<String, Object>) map.get("itemInfo"); // 배열의 자료형을 어떻게??
        List<Object> imgList =  (List<Object>) itemInfo.get("imgList");
        Map<String, Object> img = (Map<String, Object>) imgList.get(1);
        int imgCode = (int) img.get("imgCode");
        System.out.println("두번째 이미지의 imgCode 출력 : "+imgCode);

        ////////////////////////////////////////////////////////////////////////////////////////
        //map 데이터를 vo객체에 매핑하기
        ObjectMapper mapper = new ObjectMapper();
        MapDataVO data = mapper.convertValue(map, MapDataVO.class);
        System.out.println(data);
    }





}
