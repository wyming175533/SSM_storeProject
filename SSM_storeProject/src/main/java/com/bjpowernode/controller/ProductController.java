package com.bjpowernode.controller;

import com.bjpowernode.Service.ProductService;
import com.bjpowernode.pojo.ProductInfo;
import com.bjpowernode.utils.FileNameUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RequestMapping(value="/prod")
@Controller
public class ProductController {
    private  String SaveFilename="";
    public static final int PAGE_SIZE=5;
    @Autowired
    private ProductService productService;
    //@RequestMapping(value = "/split.action")
    public ModelAndView getall(){
        ModelAndView mv=new ModelAndView();
        List<ProductInfo> list=productService.split();
        mv.addObject("list",list);
        mv.setViewName("product");
        return mv;
    }
    @RequestMapping(value = "/split.action")
    public ModelAndView pageSplit(int page){
        ModelAndView mv=new ModelAndView();
        PageInfo pageInfo=productService.pageSplit(page,PAGE_SIZE);
        mv.addObject("info",pageInfo);
        mv.setViewName("product");

        return mv;
    }
    @ResponseBody
    @RequestMapping("/ajaxsplit.action")
    public void ajaxsplit(int page, HttpSession session){
        PageInfo pageInfo=productService.pageSplit(page,PAGE_SIZE);
        session.setAttribute("info",pageInfo);

    }
    @ResponseBody
    @RequestMapping("/ajaxImg.action")
    public Object ajaxImg(MultipartFile pimage, HttpServletRequest request){
        //获取要保存的文件名

         SaveFilename= FileNameUtil.getUUIDFileName()+FileNameUtil.getFileType(pimage.getOriginalFilename());
        //存储路径
        ///SSM_storeProject.../SSM_storeProjectD:\1Technology\source\SSM_storeProject\SSM_storeProject\target\SSM_storeProject-1.0\imag_big
        System.out.println(request.getContextPath()+"..."+request.getServletContext().getContextPath()+
                request.getServletContext().getRealPath("/main/webapp/image_big"));
        //path+name=D:......13223432.jpg
        String path=request.getServletContext().getRealPath("/image_big");
        try {
            System.out.println(path+File.separator+SaveFilename);
            File file=new File(path+File.separator+SaveFilename);
            pimage.transferTo(file);

        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject object=new JSONObject();
        object.put("imgurl",SaveFilename);

        return object.toString();
    }
    @RequestMapping("/save.action")
    public ModelAndView save(ProductInfo productInfo){
        productInfo.setpDate(new Date());
        productInfo.setpImage(SaveFilename);
        ModelAndView mv=new ModelAndView();
        int num=-1;
        try {
            num=productService.save(productInfo);
        } catch (Exception e) {
        }
        if(num>0){
            mv.addObject("msg","添加成功");
            mv.setViewName("forward:/prod/split.action");
        }
        else{
            mv.addObject("msg","添加失败");
          //  mv.setViewName("forward:prod/split.action");

        }

        SaveFilename="";
        return mv;
    }
    @RequestMapping("/one.action")
    public ModelAndView one(int pid,int page,HttpServletRequest request){
        ModelAndView mv=new ModelAndView();
        ProductInfo productInfo=productService.getInfoById(pid);

        mv.addObject("prod",productInfo);
        mv.addObject("page",page);
       mv.addObject("msg","");
        mv.setViewName("update");
        return mv;
    }
    @RequestMapping("/update.action")
    public ModelAndView update(ProductInfo productInfo,int page){
        ModelAndView mv=new ModelAndView();
        if(!"".equals(SaveFilename)){
            productInfo.setpImage(SaveFilename);
        }
        int num=-1;
        try {
            num=productService.updateById(productInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(num>0){
            mv.addObject("msg","更新成功");
            mv.setViewName("forward:/prod/split.action?page="+page);
        }
        else{
            mv.addObject("msg","更新失败");
            //  mv.setViewName("forward:prod/split.action");

        }
        return mv;
    }
    //单个删除
    @RequestMapping("/delete.action")
    public ModelAndView delete(int pid,int page){
        ModelAndView mv=new ModelAndView();
        int num=-1;
        try {
             num=productService.delete(pid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(num>0){
            mv.addObject("msg","删除成功");
            mv.setViewName("forward:/prod/deleteAjaxsplit.action?page="+page);
        }
        else{
            mv.addObject("msg","删除失败");
            mv.setViewName("forward:/prod/deleteAjaxsplit.action?page="+page);

        }
        return mv;
    }

    @RequestMapping("/deletebatch.action")
    public ModelAndView deletebatch(String str,int page){
        ModelAndView mv=new ModelAndView();
        String ids[]=str.split(",");

        int num=-1;
        try {
            num=productService.deletebatch(ids);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(num>0){
            mv.addObject("msg","删除成功");
            mv.setViewName("forward:/prod/deleteAjaxsplit.action?page="+page);
        }
        else{
            mv.addObject("msg","删除失败");
            mv.setViewName("forward:/prod/deleteAjaxsplit.action?page="+page);

        }


        return mv;
    }

    @ResponseBody
    @RequestMapping(value="/deleteAjaxsplit.action",produces = "text/html;charset=utf-8")
    public Object deleteAjaxsplit(int page, HttpSession session,HttpServletRequest request){
        PageInfo pageInfo=productService.pageSplit(page,PAGE_SIZE);
        session.setAttribute("info",pageInfo);
        return request.getAttribute("msg");
    }
    
}
