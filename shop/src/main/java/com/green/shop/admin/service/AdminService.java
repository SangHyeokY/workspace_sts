package com.green.shop.admin.service;

import com.green.shop.admin.vo.SearchBuyVO;
import com.green.shop.buy.vo.BuyVO;
import com.green.shop.item.vo.CategoryVO;
import com.green.shop.item.vo.ImgVO;
import com.green.shop.item.vo.ItemVO;

import java.util.List;

public interface AdminService {

    //상품 등록 + 이미지 등록 (관리자 전용)
    void insertItem(ItemVO itemVO);

    //상품 이미지 등록 (관리자 전용)
//    void insertImgs(ItemVO itemVO);

    //다음에 들어갈 ITEM_CODE 조회
    int selectNextItemCode();

    //구매기록 조회 (관리자용)
    List<BuyVO> selectBuyInfoList(SearchBuyVO searchBuyVO);

    //구매목록 상세조회 (비동기)
    BuyVO selectBuyDetail(int buyCode);

    //제품 정보 변경, 제품목록 조회
    List<ItemVO> selectItemBeforeUpdate();

    //제품 정보 변경 상세보기
    ItemVO selectItemDetail(int itemCode);

    //업데이트하기
    void updateItem(ItemVO itemVO);
}
