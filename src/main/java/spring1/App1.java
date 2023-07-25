package spring1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring1.springtest1.Config;
import spring1.springtest1_biz.UserBiz;

public class App1 {
    public static void main(String[] args) {
        //首先创建容器
        //ClassPathXmlApplicationContext  类路径下有一个xml文件来配置,来生成容器
        //FileSystemXmlApplicationContext  FileSystem任意路径 文件系统路径下的xml配置文件来生成容器
        //AnnotationConfigApplicationContext  读取注解的类(@Configuration),生成容器
        ApplicationContext container=new AnnotationConfigApplicationContext(Config.class);

//        UserDao ud= (UserDao) container.getBean("userDaoImpl");
//        ud.add("张三");

        //取业务层的类
        UserBiz ub=(UserBiz) container.getBean("userBizImpl");
        ub.add("王五");

//        UserDao udd=new UserDaoImpl();
//        udd.add("李四");
    }
}
