package me.slamke.spring.dschange;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.slamke.spring.annotation.ChooseDataSource;
import me.slamke.spring.datasource.DataSourceKey;
import me.slamke.spring.dschange.bean.Shop;
import me.slamke.spring.dschange.mapper.ShopMapper;


@Service
public class ShopService {

    @Autowired
    private DataSourceKey dataSourcekey;

    @Autowired
    private ShopMapper shopMapper;

    @ChooseDataSource("master")
    public List<Shop> findAllShop() {
        return shopMapper.findAllShop();
    }
    
    @ChooseDataSource("slave")
    public List<Shop> findAllShop2() {
        return shopMapper.findAllShop2();
    }
    
    @ChooseDataSource("master")
    public void updateShop(){
    	shopMapper.updateShop();
    }
    
    @ChooseDataSource("master")
    public void updateShop2(){
    	shopMapper.updateShop2();
    }
    
    @ChooseDataSource("master")
    public void updateShopFail(){
    	shopMapper.updateShopFail();
    }
    
    @Transactional
    public void updateJta(){
    	updateShop();
    	updateShop2();
    }
    
    @Transactional
    public void updateJtaFail(){
    	updateShop();
    	updateShopFail();
    }
}
