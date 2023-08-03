package com.yc;

import com.yc.configs.Config;
import com.yc.configs.DataSourceConfig;
import junit.framework.TestCase;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Config.class, DataSourceConfig.class})

public class Test2_DataSourceConfig extends TestCase {
    @Autowired
    private DataSourceConfig dsc;

    @Autowired
    private Environment env;

    @Autowired
    @Qualifier("dataSource")
    private DataSource ds;

    @Autowired
    @Qualifier("dbcpDataSource")
    private DataSource dbcpDataSource;


    @Test
    public void testDBCPConnection() throws SQLException {
        Assert.assertNotNull(ds.getConnection());
        Connection con=ds.getConnection();
        System.out.println(con);;
    }

    @Test
    public void testDbcpDataSource() throws SQLException {
        Assert.assertNotNull(dbcpDataSource.getConnection());
        Connection con=dbcpDataSource.getConnection();
        System.out.println(con);
    }

    @Test
    public void testPropertySource(){
        Assert.assertEquals("root",dsc.getUsername());
        System.out.println(dsc.getUsername());
    }

    @Test
    public void testEnvironment(){
        System.out.println(env.getProperty("jdbc.password")+"\t"+env.getProperty("user.home"));
    }
}
