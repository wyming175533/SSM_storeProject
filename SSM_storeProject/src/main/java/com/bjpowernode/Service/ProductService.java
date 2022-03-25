package com.bjpowernode.Service;

import com.bjpowernode.pojo.ProductInfo;
import com.bjpowernode.vo.SelectVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ProductService {
    public List<ProductInfo> split();
    public PageInfo<List<ProductInfo>> pageSplit(int pageNum,int pageSize);

    int save(ProductInfo productInfo);

    ProductInfo getInfoById(int pid);

    int updateById(ProductInfo productInfo);

    int delete(int pid);

    int deletebatch(String[] ids);
    public List<ProductInfo> SelectByEdition(SelectVo vo);

    PageInfo PageSplit(SelectVo vo, int pageSize);
}
