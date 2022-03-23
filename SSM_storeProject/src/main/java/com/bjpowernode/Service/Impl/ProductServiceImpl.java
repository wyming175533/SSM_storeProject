package com.bjpowernode.Service.Impl;

import com.bjpowernode.Service.productService;
import com.bjpowernode.mapper.ProductInfoMapper;
import com.bjpowernode.pojo.ProductInfo;
import com.bjpowernode.pojo.ProductInfoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements productService {
    @Autowired
    private ProductInfoMapper productInfoMapper;
    @Override
    public List<ProductInfo> split() {
        return productInfoMapper.selectByExample(new ProductInfoExample());
    }
}
