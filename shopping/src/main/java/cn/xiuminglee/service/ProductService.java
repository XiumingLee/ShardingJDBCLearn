package cn.xiuminglee.service;

import cn.xiuminglee.entity.ProductInfo;

import java.util.List;

/**
 * @Author Xiuming Lee
 * @Description
 */
public interface ProductService {

    //添加商品
    void createProduct(ProductInfo product);

    //查询商品
    List<ProductInfo> queryProduct(int page, int pageSize);
}
