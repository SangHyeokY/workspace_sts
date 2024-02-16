package com.green.shop.item.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class ItemVO {
    private int cateCode;
    private int itemCode;
    private String itemName;
    private int itemPrice;
    private int itemStock;
    private String itemIntro;
    private String regDate;
    private List<ImgVO> imgList;
    private String strStatus;
    private int itemStatus;
    private CategoryVO categoryVO;
}
