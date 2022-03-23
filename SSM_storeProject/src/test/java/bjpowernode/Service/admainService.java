package bjpowernode.Service;

import com.bjpowernode.pojo.Admin;

import java.util.List;

public interface admainService {
    List<Admin> login(String name,String pwd);
}
