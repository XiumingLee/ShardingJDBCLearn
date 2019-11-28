package cn.xiuminglee;

import cn.xiuminglee.dao.ProductDao;
import cn.xiuminglee.entity.ProductInfo;
import cn.xiuminglee.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @Author Xiuming Lee
 * @Description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopApplicationTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductDao productDao;

    //添加商品
    @Test
    public void testCreateProduct(){
        for (int i=10;i<20;i++){
            ProductInfo productInfo = new ProductInfo();
            productInfo.setStoreInfoId((long)(11 + i));//店铺id

            productInfo.setProductName("Java编程思想"+i);//商品名称
            productInfo.setSpec("大号");
            productInfo.setPrice(new BigDecimal(60));
            productInfo.setRegionCode("110100");
            productInfo.setDescript("Java编程思想不错！！！"+i);//商品描述
            productService.createProduct(productInfo);
        }
    }

    //查询商品
    @Test
    public void testQueryProduct(){
        List<ProductInfo> productInfos = productService.queryProduct(2, 4);
        System.out.println(productInfos);
    }

    //统计商品总数
    @Test
    public void testSelectCount(){
        int i = productDao.selectCount();
        System.out.println(i);
    }

    //分组统计商品
    @Test
    public void testSelectProductGroupList(){
        List<Map> maps = productDao.selectProductGroupList();
        System.out.println(maps);
    }
}
