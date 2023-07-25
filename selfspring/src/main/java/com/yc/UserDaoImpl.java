package com.yc;

import org.ycframework.annotation.YcRepository;

@YcRepository
public class UserDaoImpl implements UserDao{

    @Override
    public void add(String uname){
        System.out.println("dao添加了"+uname);
    }
}
