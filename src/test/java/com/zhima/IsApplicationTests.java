package com.zhima;

import com.alibaba.fastjson.JSON;
import com.zhima.h2.MyH2Dao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IsApplicationTests {


	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private MyH2Dao myH2Dao;

	@Test
	public void create() {
		System.out.println(JSON.toJSONString(myH2Dao.query()));
	}

}
