package com.bjpowernode.web.Listener;

import com.bjpowernode.Service.ProductService;
import com.bjpowernode.Service.ProductTypeService;
import com.bjpowernode.pojo.ProductType;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

@WebListener
public class TypeListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //由于存在多个监听器，不能保证那个监听器先创建，spring容器需要手动创建
        ApplicationContext applicationContext= new ClassPathXmlApplicationContext("applicationContext-*.xml");
       ProductTypeService productTypeService= (ProductTypeService) applicationContext.getBean("productTypeServiceImpl");
        List<ProductType> list=productTypeService.getTypes();
        servletContextEvent.getServletContext().setAttribute("ptlist",list);


    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
