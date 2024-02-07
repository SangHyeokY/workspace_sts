package com.green.shop.cart.service;

import com.green.shop.cart.vo.CartVO;
import com.green.shop.cart.vo.CartViewVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("cartService")
public class CartServiceImpl implements CartService {

    @Autowired
    private SqlSessionTemplate sqlsession;

    //장바구니
    @Override
    public void insertCart(CartVO cartVO) {
        //현재 내 장바구니에 지금 추가하려는 상품이 있는지 확인
        int cnt = sqlsession.selectOne("cartMapper.getCntOfCart", cartVO);

        //존재하지않으면 장바구니에 추가(insert)
        if(cnt == 0){
            sqlsession.insert("cartMapper.insertCart", cartVO);
        }
        else{
            //존재하면 수량만 변경(update)
            sqlsession.update("cartMapper.plusCartCnt", cartVO);
        }
    }

    //장바구니 목록 불러오기
    @Override
    public List<CartViewVO> selectCartList(String memberId) {
        return sqlsession.selectList("cartMapper.selectCartList", memberId);
    }

    //장바구니 삭제
    @Override
    public void deleteCart(int cartCode) {
        sqlsession.delete("cartMapper.deleteCart", cartCode);
    }

    //장바구니 수량 변경 (잘안됨)
    @Override
    public void changeCartCnt(CartVO cartVO) {
        sqlsession.update("cartMapper.changeCartCnt", cartVO);
    }

    //장바구니 선택 삭제
    @Override
    public void deleteCarts(CartVO cartVO) {
        sqlsession.delete("cartMapper.deleteCarts", cartVO);
    }

    //선택 구매
    @Override
    public List<CartViewVO> selectForBuy(CartVO cartVO) {
        return sqlsession.selectList("cartMapper.selectForBuy", cartVO);
    }


}
