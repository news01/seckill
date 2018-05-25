package com.dao.catche;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pojo.Seckill;

public class RedisDaoTest {
	
	private final static String IP = "192.168.0.109";
	private final static Integer PORT = 6379;

	@Autowired
	private RedisDao redisDao;
	
	@Before
	public void init(){
		redisDao = new RedisDao(IP, PORT);
	}

	@Test
	public void testRedisDao() {
	}

	@Test
	public void testGetSeckill() {
		Seckill seckill = new Seckill();
		seckill = redisDao.getSeckill(1007);
		System.out.println(seckill.toString());

	}

	@Test
	public void testPutSeckill() {
		Seckill seckill = new Seckill();
		seckill.setName("news");
		seckill.setSeckillId(1007L);
		seckill.setNumber(1024789);

		String rest = redisDao.putSeckill(seckill);
		System.out.println(rest);
	}

}
