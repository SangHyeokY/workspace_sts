package com.green.shop.buy.service;

import com.green.shop.buy.vo.BuyDetailVO;
import com.green.shop.buy.vo.BuyVO;
import com.green.shop.cart.vo.CartVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("buyService")
public class BuyServiceImpl implements BuyService{

    @Autowired
    private SqlSessionTemplate sqlSession;

    //다음에 들어갈 buy_code 조회
    @Override
    public int selectNextBuyCode() {
        return sqlSession.selectOne("buyMapper.selectNextBuyCode");
    }

    //장바구니 상품 구매
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertBuys(BuyVO buyVO, CartVO cartVO) {
        sqlSession.insert("buyMapper.insertBuys", buyVO);
        sqlSession.insert("buyMapper.insertBuyDetails", buyVO);
        sqlSession.delete("cartMapper.deleteCarts", cartVO);
    }

    //바로 구매
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insertBuyDirect(BuyVO buyVO, BuyDetailVO buyDetailVO) {
        sqlSession.insert("buyMapper.insertBuys", buyVO);
        sqlSession.insert("buyMapper.insertBuyDetail", buyDetailVO);
    }

    // 구매목록 조회 (사용자 용)
    @Override
    public List<BuyVO> selectBuyList(String memberId) {
        return sqlSession.selectList("buyMapper.selectBuyList", memberId);
    }
}
