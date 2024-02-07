package com.green.shop.buy.controller;

import com.green.shop.buy.service.BuyServiceImpl;
import com.green.shop.buy.vo.BuyDetailVO;
import com.green.shop.buy.vo.BuyVO;
import com.green.shop.cart.service.CartServiceImpl;
import com.green.shop.cart.vo.CartVO;
import com.green.shop.cart.vo.CartViewVO;
import com.green.shop.member.vo.MemberVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/buy")
public class BuyController {

    @Resource(name="cartService")
    private CartServiceImpl cartService;

    @Resource(name="buyService")
    private BuyServiceImpl buyService;

    //선택구매
    @GetMapping("/buyCarts")
    public String buyCarts(CartVO cartVO, HttpSession session){
        //구매 상세 테이블에 insert할 ITEM_CODE, BUY_CNT, TOTAL_PRICE 조회
        List<CartViewVO> cartViewList = cartService.selectForBuy(cartVO);

        //구매 정보 테이블에 들어갈 BUY_PRICE (구매 총 가격)
        int buyPrice = 0;
        for(CartViewVO  e : cartViewList){
            buyPrice = buyPrice + e.getTotalPrice();
        }

        //로그인 정보에서 구매자 ID 가져옴
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");
        String memberId = loginInfo.getMemberId();

        //다음에 들어갈 BUY_CODE 값을 결정 (단순 구분숫자)
        int buyCode = buyService.selectNextBuyCode();

        //구매정보 및 구매상세테이블에 insert
        BuyVO buyVO = new BuyVO();

        // 받아온 값들을 shopBuyVO에 넣기
        buyVO.setBuyPrice(buyPrice);
        buyVO.setMemberId(memberId);
        buyVO.setBuyCode(buyCode);

        List<BuyDetailVO> buyDetailList = new ArrayList<>();
        for(CartViewVO cartView :  cartViewList){
           BuyDetailVO vo = new BuyDetailVO();
           vo.setItemCode(cartView.getItemCode());
           vo.setBuyCnt(cartView.getCartCnt());
           vo.setTotalPrice(cartView.getTotalPrice());
           buyDetailList.add(vo);
        }

        // 값들을 shopBuyVO에 넣기
        buyVO.setBuyDetailList(buyDetailList);
        buyService.insertBuys(buyVO, cartVO);

        //insert
        return "redirect:/";
    }

    //바로 구매하기
    @PostMapping("/insertBuy")
    public String insertBuyDirect(BuyVO buyVO, BuyDetailVO buyDetailVO, HttpSession session){
        //다음에 들어가야 하는 buy_code 값을 조회
        // 이걸 buyVO에 넣어줌 // 남은거 ID, PRICE
        int buyCode = buyService.selectNextBuyCode();
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");
        buyVO.setBuyCode(buyCode);
        buyVO.setMemberId(loginInfo.getMemberId());
        buyVO.setBuyPrice(buyDetailVO.getTotalPrice());

        //buyDetailVO의 buyCode = buyVO의 buyCode
        buyDetailVO.setBuyCode(buyCode);

        buyService.insertBuyDirect(buyVO, buyDetailVO);

        return "redirect:/";
    }

    //구매 이력 페이지
    @GetMapping("/history")
    public String history(@RequestParam(name="page", required = false, defaultValue = "history")
                              String page, Model model, HttpSession session){
        model.addAttribute("page", page);

        //로그인 정보 조회
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");

        //구매 목록 조회
        List<BuyVO> buyList = buyService.selectBuyList(loginInfo.getMemberId());
        model.addAttribute("buyList", buyList);

//        List<CartViewVO> cartList = cartService.selectCartList(loginInfo.getMemberId());
//        model.addAttribute("cartList", cartList);

        for(BuyVO e : buyList){
            System.out.println(e);
        }

        return "content/buy/history";
    }

}

