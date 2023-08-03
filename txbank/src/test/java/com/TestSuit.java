package com;

import com.yc.Test1;
import com.yc.Test2_DataSourceConfig;
import com.yc.configs.DataSourceConfig;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RunWith(Suite.class)
@Suite.SuiteClasses({Test1.class, Test2_DataSourceConfig.class})
@Log4j2
public class TestSuit {
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
        log.info(con);
    }

    @Test
    public void testDbcpDataSource() throws SQLException {
        Assert.assertNotNull(dbcpDataSource.getConnection());
        Connection con=dbcpDataSource.getConnection();
        log.info(con);
    }

    @Test
    public void testPropertySource(){
        Assert.assertEquals("root",dsc.getUsername());
    }
}
