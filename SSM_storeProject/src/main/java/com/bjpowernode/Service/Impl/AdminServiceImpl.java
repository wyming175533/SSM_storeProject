package com.bjpowernode.Service.Impl;

import com.bjpowernode.Service.AdminService;
import com.bjpowernode.mapper.AdminMapper;
import com.bjpowernode.pojo.Admin;
import com.bjpowernode.pojo.AdminExample;
import com.bjpowernode.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Override
    public List<Admin> login(String name, String pwd) {
    //根据用户名和密码再数据库中查找，找到返回该对象，找不到返回空
        AdminExample example=new AdminExample();
        example.createCriteria().andANameEqualTo(name);
        pwd= MD5Util.getMD5(pwd);
        example.createCriteria().andAPassEqualTo(pwd);
        System.out.println(pwd);
        List<Admin> list=adminMapper.selectByExample(example);
        System.out.println(list);
        if(list.size()>0){
            if(pwd.equals(list.get(0).getaPass())){
                return list;
            }
        }
        return null;

    }
}
