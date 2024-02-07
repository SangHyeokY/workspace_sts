package com.green.shop.item.controller;

import com.green.shop.item.service.ItemServiceImpl;
import com.green.shop.item.vo.ImgVO;
import com.green.shop.item.vo.ItemVO;
import com.green.shop.util.UploadUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

//상품과 관련된 모든 페이지 이동을 처리
@Controller
@RequestMapping("/item")
public class ItemController {

    //item
    @Resource(name="itemService")
    private ItemServiceImpl itemService;

    //상품 목록 페이지
    @GetMapping("/list")
    public String list(Model model){
        //상품 목록 조회
        List<ItemVO> itemList =  itemService.selectItemList();
        model.addAttribute("itemList", itemList);
        return "content/item/item_list";
    }

    //상품 상세 정보 조회
    @GetMapping("/itemDetail")
    public String detailItem(@RequestParam(name="itemCode") int itemCode, Model model){
        ItemVO vo = itemService.selectItemDetail(itemCode);

        model.addAttribute("item", vo);
        return "content/item/item_detail";
    }


}
