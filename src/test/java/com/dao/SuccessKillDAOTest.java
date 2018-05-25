package com.dao;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pojo.SuccessKilled;

//配置spring和Junit整合，Junit启动时加载SpringIOC容器
@RunWith(SpringJUnit4ClassRunner.class)
// 告诉Junit spring的配置文件
@ContextConfiguration({ "classpath:applicationContext.xml" })
public class SuccessKillDAOTest {

	@Resource
	private SuccessKillDAO successKillDAO;

	@Test
	public void testInsertSuccessKilled() {
		long phone = 13929266764L;
		int resu = successKillDAO.insertSuccessKilled(100, phone,0);
		System.out.println(resu);
	}

	@Test
	public void testQueryByIdWithSeckill() {
		long phone = 13929266764L;
		SuccessKilled killed = successKillDAO.queryByIdWithSeckill(100, phone);
		System.out.println(killed);
//		assertNull(killed);
	}
}
