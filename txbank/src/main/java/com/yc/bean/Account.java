package com.yc.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor //待所有参数的构造方法
@NoArgsConstructor  //空参数的构造方法
@ToString    //生产toString
public class Account implements Serializable {
    private int accountid;
    private double money;
}
