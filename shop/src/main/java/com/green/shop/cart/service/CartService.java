package com.green.shop.cart.service;

import com.green.shop.cart.vo.CartVO;
import com.green.shop.cart.vo.CartViewVO;

import java.util.List;

public interface CartService {

    //장바구니 넣기
    void insertCart(CartVO cartVO);

    //장바구니 목록 조회
    List<CartViewVO> selectCartList(String memberId);

    //장바구니 삭제
    void deleteCart(int cartCode);

    //장바구니 수량 변경 (cartCode, cartCnt 받아야함)
    void changeCartCnt(CartVO cartVO);

    //장바구니 상품들 삭제
    void deleteCarts(CartVO cartVO);

    // 선택 구매 정보
    List<CartViewVO> selectForBuy(CartVO cartVO);


}
