package cn.xiuminglee.service.impl;

import cn.xiuminglee.dao.ProductDao;
import cn.xiuminglee.entity.ProductDescript;
import cn.xiuminglee.entity.ProductInfo;
import cn.xiuminglee.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Xiuming Lee
 * @Description
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public void createProduct(ProductInfo productInfo) {
        ProductDescript productDescript =new ProductDescript();
        //设置商品描述 信息
        productDescript.setDescript(productInfo.getDescript());
        //调用dao向商品信息表
        productDao.insertProductInfo(productInfo);
        //将商品信息id设置到productDescript
        productDescript.setProductInfoId(productInfo.getProductInfoId());
        //设置店铺id
        productDescript.setStoreInfoId(productInfo.getStoreInfoId());
        //向商品描述信息表插入数据
        productDao.insertProductDescript(productDescript);
    }

    @Override
    public List<ProductInfo> queryProduct(int page, int pageSize) {
        int start = (page - 1) * pageSize;
        return productDao.selectProductList(start,pageSize);
    }
}
