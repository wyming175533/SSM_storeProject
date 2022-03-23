package com.bjpowernode.controller;

import com.bjpowernode.Service.AdminService;
import com.bjpowernode.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RequestMapping(value = "/admin")
@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;
    @RequestMapping(value = "/login.action",method = RequestMethod.POST)
    public ModelAndView login(String name, String pwd) {
        ModelAndView mv=new ModelAndView();
        List<Admin> list=adminService.login(name,pwd);
        if(list!=null){
            mv.addObject("admin",list.get(0));
            System.out.println("22222222");

            mv.setViewName("main");
        }else
        {
            mv.addObject("errmsg","用户名或密码不正确");
            System.out.println("11111111111");
            mv.setViewName("login");
        }


        return mv;
    }

}
