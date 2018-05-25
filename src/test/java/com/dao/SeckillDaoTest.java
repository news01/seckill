package com.dao;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pojo.Seckill;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:applicationContext.xml" })
public class SeckillDaoTest {

	@Resource
	private SeckillDao seckilldao;

	@Test
	public void testQueryById() {
		int id = 100;
		Seckill seckill = seckilldao.queryById(id);
		System.out.println(seckill.toString());

	}

	@Test
	public void testReduceNumber() {
		int resu = seckilldao.reduceNumber(1, new Date());
		System.out.println(resu);
	}

	@Test
	public void testQueryAll() {

		List<Seckill> seckills = seckilldao.queryAll(0, 2);
		for (Iterator iterator = seckills.iterator(); iterator.hasNext();) {
			Seckill seckill = (Seckill) iterator.next();
			System.out.println(seckill.toString());
		}
	}

}
