package me.slamke.spring.dschange;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import me.slamke.spring.dschange.bean.Shop;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:applicationContext.xml")
public class TestChangeDs {

    @Autowired
    private ShopService shopService;

    @Test
    @Rollback(false)
    public void testFindAllShop(){
        List<Shop> shopList1 = shopService.findAllShop();
        System.out.println(shopList1);

        System.out.println("######");
    }
    @Test
    public void fromTestDB(){
        List<Shop> shopList = shopService.findAllShop2();
      
        System.out.println(shopList);
        System.out.println("$$$$$$");
    }
    
    @Test
    public void testJTA(){
    	shopService.updateJta();
    	System.out.println("SUCCESS JTA");
    }

}
