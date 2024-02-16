package com.green.shop.member.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class MapDataVO {
    private int cartCode;
    private MapDataMember memberInfo;
    private ItemInfo itemInfo;
}

@Getter
@Setter
@ToString
class MapDataMember{
    private String memberId;
    private String memberName;
}

@Getter
@Setter
@ToString
class ItemInfo{
    private int itemCode;
    private String itemName;
    private int itemPrice;
    private List<imgInfo> imgList;
}

@Getter
@Setter
@ToString
class imgInfo{
    private String imgName;
    private int imgCode;
}