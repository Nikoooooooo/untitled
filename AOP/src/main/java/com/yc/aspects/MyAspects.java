package com.yc.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Aspect
public class MyAspects {
    //切入点表达式:正则表达式  筛选目标类中那些方法加增强

    @Pointcut("execution(* com.yc.biz.*.make*(..))")
    private void a(){

    }
    @Before("a()")
    public void recordTime(){
        Date d=new Date();
        System.out.println("=========下单的时间"+d);
    }

    @AfterReturning("a()")
    public void senEmail(){
        System.out.println("调用数据库查询此下单用户的email,包装信息,发送信息中间做kafka/mq");
    }

    @AfterReturning("a()")
    public void recordParams(JoinPoint jp){
        //从jp中可以取出一些信息 make*()方法的信息
        System.out.println("增强的方法为:"+jp.getSignature());
        System.out.println("增强的目标为:"+jp.getTarget());
        System.out.println("参数:");
        Object[] params=jp.getArgs();
        for (Object o:params){
            System.out.println(o);
        }
    }

    @Pointcut("execution(* com.yc.biz.*.findOrderId*(String))")
    private void b(){

    }

    //正常是访问redis
    private Map<String,Long> map=new ConcurrentHashMap<>();
    //统计每个商品被查询的次数
    @AfterReturning("b()")
    public void recordPnameCount(JoinPoint jp){
        Object[] objs=jp.getArgs();
        String pname= (String) objs[0];
        Long num=1L;
        if (map.containsKey(pname)){
            num=map.get(pname);
            num++;
        }
        map.put(pname,num);
        System.out.println("统计结果:"+map);
    }

    //查询是同一个商品名有不同的返回值 pid ,请统计频率
    //如何获取方法的返回值
    @Pointcut("execution(int com.yc.biz.*.findPid*(String))")
    private void c(){}
    private Map<String,Long> map2=new ConcurrentHashMap<>();
    //统计每个 商品名 编号 被查询的次数
    @AfterReturning(pointcut = "c()",returning = "retValue")
    public void recordPnameCount2(JoinPoint jp,int retValue){//DI方式注入
        Object[] objs=jp.getArgs();
        String pname=(String)objs[0];
        Long num=1L;
        if (map2.containsKey(pname)){
            num=map2.get(pname);
            num++;
        }
        map2.put(pname+":"+retValue,num);
        System.out.println("统计结果:"+map2);
    }

    @Pointcut("execution(* com.yc.biz.*.find*(..))")
    private void d(){}

    @Around("d()")
    public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {  //pjp就是被调用的find*()
        long start=System.currentTimeMillis();
        Object retVal=pjp.proceed();   //返回值find*()
        long end=System.currentTimeMillis();
        System.out.println("方法执行时长为:"+(end-start));
        return retVal;
    }
}
