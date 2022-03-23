package com.bjpowernode.Service;

import com.bjpowernode.pojo.Admin;

import java.util.List;

public interface AdminService {
    List<Admin> login(String name,String pwd);
}
