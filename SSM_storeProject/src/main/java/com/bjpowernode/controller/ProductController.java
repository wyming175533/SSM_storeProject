package com.bjpowernode.controller;

import com.bjpowernode.Service.ProductService;
import com.bjpowernode.pojo.ProductInfo;
import com.bjpowernode.utils.FileNameUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
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
import java.util.List;

@RequestMapping(value="/prod")
@Controller
public class ProductController {
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
    public ModelAndView pageSplit(){
        ModelAndView mv=new ModelAndView();
        PageInfo pageInfo=productService.pageSplit(1,PAGE_SIZE);
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

        String SaveFilename= FileNameUtil.getUUIDFileName()+FileNameUtil.getFileType(pimage.getOriginalFilename());
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
    
}
