package com.yc.configs;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.DriverManager;

@Configuration
@PropertySource("classpath:db.properties")
@Data  //lomhbok创建 get/set
@Log4j2
@EnableTransactionManagement     //启用事务管理器
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

    @Bean
    public TransactionManager dataSourceTransactionManager(@Autowired DataSource ds){
        DataSourceTransactionManager tx=new DataSourceTransactionManager();
        tx.setDataSource(ds);
        return tx;
    }

    //参数:第三方的框架中的类  用Bean托管
    @Bean(initMethod = "init",destroyMethod = "close")
    @Primary
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
