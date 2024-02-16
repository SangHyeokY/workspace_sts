package com.green.shop.admin.controller;

import com.green.shop.admin.service.AdminServiceImpl;
import com.green.shop.admin.vo.SearchBuyVO;
import com.green.shop.buy.service.BuyServiceImpl;
import com.green.shop.buy.vo.BuyDetailVO;
import com.green.shop.buy.vo.BuyVO;
import com.green.shop.item.service.ItemServiceImpl;
import com.green.shop.item.vo.CategoryVO;
import com.green.shop.item.vo.ImgVO;
import com.green.shop.item.vo.ItemVO;
import com.green.shop.member.vo.MemberVO;
import com.green.shop.util.ConstantVariable;
import com.green.shop.util.UploadUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    //item
    @Resource(name="itemService")
    private ItemServiceImpl itemService;

    //admin
    @Resource(name="adminService")
    private AdminServiceImpl adminService;

    //buy
    @Resource(name="buyService")
    private BuyServiceImpl buyService;


    //상품 등록 페이지로 이동
    @GetMapping("/regItemForm")
    public String regItemForm(Model model){
        //카테고리 목록 조회
        List<CategoryVO> categoryList = itemService.selectCategoryList();
        model.addAttribute("categoryList", categoryList);
        //페이지 전달
        model.addAttribute("page", 2);
        return "content/admin/reg_item_form";
    }

    //상품 등록하기 (관리자 전용)
    @PostMapping("/regItem")
    public String regItem(ItemVO itemVO, @RequestParam(name="mainImg") MultipartFile mainImg
                                       , @RequestParam(name="subImgs") MultipartFile[] subImgs){

        //파일 첨부 기능 ------------------------------------------------------------------------//
        //메인 이미지 업로드
        ImgVO mainimgVO = UploadUtil.uploadFile(mainImg);          //util에서 만든걸 불러옴

        //상세 이미지들 업로드
        List<ImgVO> imgList = UploadUtil.multiUploadFile(subImgs); //for문으로 출력

        //다음에 들어갈 ITEM_CODE 조회 -----------------------------------------------------------//
        int itemCode = adminService.selectNextItemCode();

        //제품 등록 쿼리 실행 --------------------------------------------------------------------//
        //조회한 ITEM_CODE 값을 itemVO에 넣기
        itemVO.setItemCode(itemCode);

        //상품 이미지 정보 등록 쿼리 실행 --------------------------------------------------------//

        //현재 itemVO에는 상품명, 가격, 상품소개, cateCode만 있음

        //이미지 등록시 채워줘야 하는 모든 데이터를 갖는 리스트를 생성
        mainimgVO.setItemCode(itemCode);
        for(ImgVO img : imgList){
            img.setItemCode(itemCode);
        }
//      이거 두개 합쳐야 됨
        imgList.add(mainimgVO);
        itemVO.setImgList(imgList);

        System.out.println(itemVO);
        adminService.insertItem(itemVO); //itemVO

        return "redirect:/admin/regItemForm";
    }

        // 관리자 페이지
        @RequestMapping("/adminHistory")
        public String adminHistory(Model model, SearchBuyVO searchBuyVO){
            //구매 목록 조회
            List<BuyVO> buyList = adminService.selectBuyInfoList(searchBuyVO);
            model.addAttribute("buyList", buyList);
            //페이지 전달
            model.addAttribute("page", 1);
            return "content/admin/admin_history";
        }

        //상세구매 내역 조회
        @ResponseBody
        @PostMapping("/selectBuyDetail")
        public BuyVO selectBuyDetail(@RequestParam(name="buyCode") int buyCode){
            //구매 상세 내역 조회 (비동기)
            BuyVO buyVO = adminService.selectBuyDetail(buyCode);
            return buyVO;
        }

    //제품 정보목록 조회
    @GetMapping("/updateItem")
    public String selectItemBeforeUpdate(Model model,
                  @RequestParam(name="itemCode", required = false, defaultValue = "0") int itemCode){
                    //페이지 내용 변경되어도 itemCode 값 유지
        //목록조회
        List<ItemVO> updateList = adminService.selectItemBeforeUpdate();
        model.addAttribute("updateList", updateList);
        //페이지 전달
        model.addAttribute("page", 4);
        model.addAttribute("updateItemCode", itemCode);
        return "content/admin/update_item";
    }

    //제품 정보목록 변경화면의 상품목록 테이블의 행 클릭 시
    // 상품의 상세정보를 조회 (비동기)
    @ResponseBody
    @PostMapping("/selectItemDetail")
    public Map<String, Object> selectItemDetail(@RequestParam(name="itemCode") int itemCode){
        //상품 상세정보 조회
        ItemVO itemDetail = adminService.selectItemDetail(itemCode);
        //카테고리 목록 조회
        List<CategoryVO> cateList = itemService.selectCategoryList();
        //위 두 데이터를 담을 수 있는 map 객체 생성
        Map<String, Object> map = new HashMap<>();
        map.put("itemDetail", itemDetail);
        map.put("cateList", cateList);
        return map;
    }

    //상품정보 변경(동기)
    @PostMapping("/updateItemDetail")
    public String updateItem(ItemVO itemVO){
        //목록정보 업데이트
        adminService.updateItem(itemVO);
        return "redirect:/admin/updateItem?itemCode=" + itemVO.getItemCode();
    }





}
