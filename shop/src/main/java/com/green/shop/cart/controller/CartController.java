package com.green.shop.cart.controller;

import com.green.shop.cart.service.CartServiceImpl;
import com.green.shop.cart.vo.CartVO;
import com.green.shop.cart.vo.CartViewVO;
import com.green.shop.member.vo.MemberVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Resource(name="cartService")
    private CartServiceImpl cartService;

    //장바구니 등록
    @ResponseBody
    @PostMapping ("/insertCart")
    public void insertCart(CartVO cartVO, HttpSession session){
        //cartVO에 받은 정보를 보내기 //itemCode //cartCnt //memberId
        MemberVO loginInfo = (MemberVO)session.getAttribute("loginInfo");
        cartVO.setMemberId(loginInfo.getMemberId());
        //insert 받은 정보를 저장해야 함
        cartService.insertCart(cartVO);
    }

    //장바구니 목록 / 불러오기
    @GetMapping("/list")
    public String list(HttpSession session, Model model,
                       @RequestParam(name="page", required = false, defaultValue = "cartList") String page){
        MemberVO loginInfo = (MemberVO) session.getAttribute("loginInfo");
        List<CartViewVO> cartList = cartService.selectCartList(loginInfo.getMemberId());
        model.addAttribute("cartList", cartList);

        //총 가격을 계산한 후 html로 전달
        int sum = 0;
        for(CartViewVO e : cartList){
            sum += e.getTotalPrice();
        }
       // model.addAttribute("finalPrice", sum);
        model.addAttribute("page", page);
        return "content/cart/cart_list";
    }

    //장바구니 삭제
    @GetMapping("/deleteCart")
    public String deleteCart(@RequestParam(name="cartCode") int cartCode){
        cartService.deleteCart(cartCode);
        return "redirect:/cart/list?page=cartList";
    }

    //장바구니 수량변경 (비동기)
    @ResponseBody
    @PostMapping("/cntChange")
    public void changeCartCnt(CartVO cartVO){
        cartService.changeCartCnt(cartVO);
    }

    //장바구니 선택 삭제
    @GetMapping("/deleteCarts")
    public String deleteCarts(CartVO cartVO){
        //배열
        cartService.deleteCarts(cartVO);
        return "redirect:/cart/list";
    }
}
