package spring1.springtest1_biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import spring1.springtest1_dao.UserDao;

import javax.annotation.Resource;

@Service
public class UserBizImpl implements UserBiz{

    //将dao层的对象主人注入到biz  di依赖注入(将spring容器中托管的userDao的对象传到此处)
     @Resource(name="userDaoImpl")    //由spring容器根据id名为 userDaoImpl到容器中找这个实例,并注入

//    @Autowired  //根据类型来完成注入 ,在spring容器中根据类型 UserDao接口找实例
//    @Qualifier("userDaoImpl")
    private UserDao userDao;

    public UserBizImpl(){
        System.out.println("userBizImpl的构造");
    }

    @Override
    public void add(String uname) {
        userDao.add(uname);
    }
}
