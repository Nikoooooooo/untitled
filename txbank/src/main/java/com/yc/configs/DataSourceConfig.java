package com.yc.configs;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.DriverManager;

@Configuration
@PropertySource("classpath:db.properties")
@Data
public class DataSourceConfig {
    //利用Di将db.properties中的内容注入
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.driverclass}")
    private String driverclass;
    @Value("#{T(Runtime).getRuntime().availableProcessors()*2}")
    private int cpuCount;

    //参数:第三方的框架中的类  用Bean托管
    @Bean(initMethod = "init",destroyMethod = "close")
    public DruidDataSource druidDataSource(){
        DruidDataSource dds=new DruidDataSource();
        dds.setDriverClassName(driverclass);
        dds.setUrl(url);
        dds.setUsername(username);
        dds.setPassword(password);
        //以上只是配置了参数,并没有创建连接池
        return dds;
    }


    @Bean  //IOC注解,托管第三方Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource=new DriverManagerDataSource();
        dataSource.setDriverClassName(driverclass);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public DataSource dbcpDataSource(){
        BasicDataSource dataSource=new BasicDataSource();
        dataSource.setDriverClassName(driverclass);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
}
