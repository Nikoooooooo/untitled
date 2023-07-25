package org.ycframework.context;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.ycframework.annotation.YcComponentScan;
import org.ycframework.annotation.YcConfiguration;

import java.util.*;

public class YcAnnotationConfigApplicaitonContext implements YcApplicationContext{
    private Logger logger= LoggerFactory.getLogger(YcAnnotationConfigApplicaitonContext.class);
    //存每个待托管的Bean的自定义信息
    private Map<String,YcBeanDefinition> beanDefinitionMap=new HashMap<>();
    //存每个实例化后的bean
    private Map<String,Object> beanMap=new HashMap<>();
    //存系统属性,db.properties
    private Properties pros;
    public YcAnnotationConfigApplicaitonContext(Class... configClasses){
       //读取系统的属性
        pros=System.getProperties();
        List<String> toScanPackagePath=new ArrayList<>();
        for (Class cls: configClasses){
            if (cls.isAnnotationPresent(YcConfiguration.class)==false){
                continue;
            }
            //扫描配置类上的 @YcComponentScan 注解,读取要扫描的类
            if (cls.isAnnotationPresent(YcComponentScan.class)){
                //如果有则说明此配置类上有 @YcComponentScan ,则读取 basePackages
                YcComponentScan ycComponentScan= (YcComponentScan) cls.getAnnotation(YcComponentScan.class);
                String[] basePackages=ycComponentScan.basePackage();
                if (basePackages==null ||basePackages.length<=0){
                    basePackages=new String[1];
                    basePackages[0]=cls.getPackage().getName();
                }
                logger.info(cls.getName()+"类上有@YcComponentScan注解,它要扫描的路径:"+basePackages[0]);
            }
            //将这些包中的带有IPC注解的类 加载到一个Map中
        }
    }

    @Override
    public Object getBean(String beanid) {
        return null;
    }
}
