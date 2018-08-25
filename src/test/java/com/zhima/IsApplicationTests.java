package com.zhima;

import com.zhima.kit.RedisKit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IsApplicationTests {

	@Autowired
	private RedisKit redisKit;

	@Test
	public void contextLoads() {
		redisKit.set("1","1313131313");
		System.out.println(redisKit.get("1"));
	}

}
