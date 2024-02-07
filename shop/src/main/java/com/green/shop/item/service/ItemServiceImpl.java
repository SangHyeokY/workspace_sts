package com.green.shop.item.service;

import com.green.shop.item.vo.CategoryVO;
import com.green.shop.item.vo.ItemVO;
import groovy.lang.Category;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("itemService")
public class ItemServiceImpl implements ItemService{

    @Autowired
    private SqlSessionTemplate sqlSession;

    //카테고리 목록 조회
    @Override
    public List<CategoryVO> selectCategoryList() {
        return sqlSession.selectList("itemMapper.selectCategoryList");
    }

    //목록 불러오기
    @Override
    public List<ItemVO> selectItemList() {
        return sqlSession.selectList("itemMapper.selectItemList");
    }

    //상품 정보 상세 조회
    @Override
    public ItemVO selectItemDetail(int itemCode) {
        return sqlSession.selectOne("itemMapper.selectItemDetail", itemCode);
    }


}
