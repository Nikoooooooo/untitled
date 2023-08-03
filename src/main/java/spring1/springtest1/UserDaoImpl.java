package spring1.springtest1;

import org.springframework.stereotype.Repository;
import spring1.springtest1_dao.UserDao;

import javax.annotation.Resource;

@Repository
public class UserDaoImpl implements UserDao {
    public UserDaoImpl(){
        System.out.println("UserDaoImpl类的构造方法");
    }


    @Override
    public void add(String uname) {
        System.out.println("添加了:"+uname);
    }
}
