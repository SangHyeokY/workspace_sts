package com.green.shop.admin.service;

import com.green.shop.buy.vo.BuyVO;
import com.green.shop.item.vo.ImgVO;
import com.green.shop.item.vo.ItemVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("adminService")
public class AdminServiceImpl implements AdminService{

    @Autowired
    private SqlSessionTemplate sqlSession;

    //상품 등록 + 이미지 등록 (관리자 전용)
    //Transactional 어노테이션이 불어있는
    //메소드 내부의 쿼리 실행은
    //모든 쿼리가 실행 성공시 commit
    //쿼리 중 하나라도 실패시 rollback
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertItem(ItemVO itemVO) {
        sqlSession.insert("adminMapper.insertItem", itemVO);
        sqlSession.insert("adminMapper.insertImgs", itemVO);
    }

    //다음에 들어갈 ITEM_CODE
    @Override
    public int selectNextItemCode() {
        return sqlSession.selectOne("adminMapper.selectItemCode");
    }

    //구매기록 조회 (관리자용)
    @Override
    public List<BuyVO> selectBuyInfoList() {
       return sqlSession.selectList("adminMapper.selectBuyInfoList");
    }

    //구매기록 상세 조회 (비동기)
    @Override
    public BuyVO selectBuyDetail(int buyCode){
        return sqlSession.selectOne("adminMapper.selectBuyDetail", buyCode);
    }

    //제품 정보 변경, 제품목록 조회
    @Override
    public List<ItemVO> selectItemBeforeUpdate() {
        return sqlSession.selectList("adminMapper.selectItemBeforeUpdate");
    }




}
