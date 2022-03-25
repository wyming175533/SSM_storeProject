package com.bjpowernode.Service.Impl;

import com.bjpowernode.Service.ProductService;
import com.bjpowernode.mapper.ProductInfoMapper;
import com.bjpowernode.pojo.ProductInfo;
import com.bjpowernode.pojo.ProductInfoExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductInfoMapper productInfoMapper;
    @Override
    public List<ProductInfo> split() {
        return productInfoMapper.selectByExample(new ProductInfoExample());
    }
    public PageInfo<List<ProductInfo>> pageSplit(int pageNum,int pageSize){
        //分页插件进行分页设置
        PageHelper.startPage(pageNum,pageSize);
        ProductInfoExample productInfoExample=new ProductInfoExample();
        productInfoExample.setOrderByClause("p_id desc");
        //获取查询，查询所有并根据id降序排序
          List<ProductInfo> list=productInfoMapper.selectByExample(productInfoExample);
          //创建pageINfo，其中页数，总条数，等各种属性已经封装好
          PageInfo pageInfo=new PageInfo(list);
          return pageInfo;

    }
}
