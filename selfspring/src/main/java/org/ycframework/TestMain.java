package org.ycframework;


import com.yc.MyConfig;
import com.yc.UserBiz;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ycframework.context.YcAnnotationConfigApplicaitonContext;
import org.ycframework.context.YcApplicationContext;

public class TestMain {
    public static void main(String[] args) {


        YcApplicationContext ac=new YcAnnotationConfigApplicaitonContext(MyConfig.class);
//        UserBiz ub= (UserBiz) ac.getBean("userBizImpl");
//        ub.add("张三");
    }
}
