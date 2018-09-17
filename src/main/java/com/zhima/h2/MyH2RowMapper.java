package com.zhima.h2;

import com.zhima.h2.po.MyInfo;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by superz on 2018/9/17.
 */
public class MyH2RowMapper implements RowMapper {
    @Nullable
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        MyInfo myInfo=new MyInfo();
        myInfo.setId(resultSet.getInt("id"));
        myInfo.setDiskPath(resultSet.getString("disk_path"));
        myInfo.setPackageName(resultSet.getString("package_name"));
        myInfo.setPassword(resultSet.getString("password"));
        myInfo.setTableName(resultSet.getString("table_name"));
        myInfo.setUrl(resultSet.getString("url"));
        myInfo.setUser(resultSet.getString("user"));
        return myInfo;
    }
}
