package com.bjpowernode.controller;

import com.bjpowernode.Service.productService;
import com.bjpowernode.pojo.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping(value="/prod")
@Controller
public class ProductController {
    @Autowired
    private productService productService;
    @RequestMapping(value = "/split.action")
    public ModelAndView getall(){
        ModelAndView mv=new ModelAndView();
        List<ProductInfo> list=productService.split();
        mv.addObject("list",list);
        mv.setViewName("product");
        return mv;
    }
    
}
