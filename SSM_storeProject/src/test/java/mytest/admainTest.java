package mytest;

import com.bjpowernode.utils.MD5Util;
import org.junit.Test;

public class admainTest {
    @Test
    public void md5Test(){
      String pwd= MD5Util.getMD5("000000");
        System.out.println(pwd);
    }
}
