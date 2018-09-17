package com.zhima.h2;

import com.zhima.h2.po.MyInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.List;

/**
 * Created by superz on 2018/9/17.
 */
@Component
public class MyH2Dao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void runCreate() {
            jdbcTemplate.execute("DROP TABLE IF EXISTS myinfo");
            jdbcTemplate.execute("CREATE TABLE myinfo ( id INT IDENTITY PRIMARY KEY, table_name VARCHAR(20),package_name VARCHAR(60),url VARCHAR(100),user VARCHAR(15),password VARCHAR(20),driver VARCHAR(50),disk_path VARCHAR(60) )");
    }

    public void runInsert() {
            jdbcTemplate.batchUpdate("INSERT INTO myinfo (table_name,package_name,url,user,password,driver,disk_path) " +
                    "VALUES ('t_funds','com.tangshixiong.funds','jdbc:mysql://rm-m5e105ep54w2y9i792o.mysql.rds.aliyuncs.com/tsx','tsxdba'," +
                    "'@Tsx2017','com.mysql.jdbc.Driver','D:/myCodeGen/')");

    }

    public List<MyInfo> query() {
        List<MyInfo> myInfos=jdbcTemplate.query("select * from myInfo",new MyH2RowMapper());
        return myInfos;
    }
}
