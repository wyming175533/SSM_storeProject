package mytest;

import com.bjpowernode.mapper.ProductInfoMapper;
import com.bjpowernode.pojo.ProductInfo;
import com.bjpowernode.vo.SelectVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-dao.xml","classpath:applicationContext-service.xml"})
public class sqlTest {
    @Autowired
    private ProductInfoMapper productInfoMapper;
    @Test
    public void mysqltest(){
        SelectVo vo=new SelectVo();
        List<ProductInfo> list=productInfoMapper.selectByEdition(vo);
        System.out.println(list);
    }

    @Test
    public void mysqltest2(){
        SelectVo vo=new SelectVo();
        vo.setHprice(1000);

        List<ProductInfo> list=productInfoMapper.selectByEdition(vo);
        System.out.println(list);
    }
    @Test
    public void mysqltest3(){
        SelectVo vo=new SelectVo();
      vo.setTypeid(1);

        List<ProductInfo> list=productInfoMapper.selectByEdition(vo);
        System.out.println(list);
    }
}
