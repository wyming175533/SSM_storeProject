package com.bjpowernode.Service;

import com.bjpowernode.pojo.ProductInfo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ProductService {
    public List<ProductInfo> split();
    public PageInfo<List<ProductInfo>> pageSplit(int pageNum,int pageSize);
}
