package com.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dto.Exposer;
import com.dto.SeckillExecution;
import com.pojo.Seckill;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:applicationContext.xml" })
public class SeckillServiceTest {

	private Logger logger = Logger.getLogger(SeckillServiceTest.class);

	@Autowired
	private SeckillService seckillService;

	@Test
	public void testGetSeckillList() {
		List<Seckill> seckills = seckillService.getSeckillList();
		for (Seckill seckill : seckills) {
			System.out.println(seckill.toString());
		}

	}

	@Test
	public void testGetSeckillById() {
		long id = 100;
		Seckill seckill = seckillService.getSeckillById(id);
		System.out.println(seckill.toString());

	}

	@Test
	public void testExportSeckillUrl() {
		long id = 100;
		Exposer exposer = seckillService.exportSeckillUrl(id);
		long userPhone = 13929266761L;
		String md5 = exposer.getMd5();
		SeckillExecution seckillExecution = seckillService.executeSeckill(id, userPhone, md5);
		logger.info(seckillExecution.toString());
		
		

	}

	@Test
	public void testExecuteSeckill() {
		long id = 100;
		long userPhone = 13929266762L;
		String md5 = "69b309ff1f0d32430cea3cda94c4ba1a";
		SeckillExecution seckillExecution = seckillService.executeSeckill(id, userPhone, md5);
		logger.info(seckillExecution.toString());

	}

}
