package me.slamke.spring.dschange.mapper;

import java.util.List;

import me.slamke.spring.dschange.bean.Shop;

public interface ShopMapper extends SqlMapper {

    public List<Shop> findAllShop();
    
    public List<Shop> findAllShop2();
    
    public void updateShop();
    
    public void updateShop2();
    
    public void updateShopFail();

}
