package com.bjpowernode.Service.Impl;

import com.bjpowernode.Service.ProductTypeService;
import com.bjpowernode.mapper.ProductTypeMapper;
import com.bjpowernode.pojo.ProductType;
import com.bjpowernode.pojo.ProductTypeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("productTypeServiceImpl")
public class ProductTypeServiceImpl implements ProductTypeService {
    @Autowired
    private ProductTypeMapper productTypeMapper;
    @Override
    public List<ProductType> getTypes() {
        ProductTypeExample productTypeExample=new ProductTypeExample();
        List<ProductType> list=productTypeMapper.selectByExample(productTypeExample);
        return list;
    }
}
