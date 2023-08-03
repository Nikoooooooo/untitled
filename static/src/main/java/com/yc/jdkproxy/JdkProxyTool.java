package com.yc.jdkproxy;

import java.lang.annotation.Target;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxyTool implements InvocationHandler {
    //目标类
    private Object target;

    public JdkProxyTool(Object target) {
        this.target = target;
    }

    //生成代理对象的方法
    public Object createProxy(){
        Object proxy= Proxy.newProxyInstance(JdkProxyTool.class.getClassLoader(), target.getClass().getInterfaces(),this);
        return proxy;
    }
    //当在主程序中调用生成的Proxy中的方法,会自动回调这个invoke(),应在这个invoke()加入增强,切面这些功能
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //以反射的方式读取下面的@Pointcut中的切入表达式    在spring底层,用aspectj来读取表达式
        if (method.getName().startsWith("add")){
            showHello();
        }
        Object returnValue=method.invoke(target,args);
        return returnValue;
    }


    public void showHello(){
        System.out.println("hello");
    }
}
