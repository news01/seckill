package com.service;

import java.util.List;
import java.util.Map;

import com.dto.Exposer;
import com.dto.SeckillExecution;
import com.exception.RepeatKillException;
import com.exception.SeckillCloseException;
import com.exception.SeckillException;
import com.pojo.Seckill;

/**
 * 业务接口
 * @author Administrator
 *
 */
public interface SeckillService {
	
	
	/**
	 * 查询所有秒杀记录
	 * 时间：2018年5月10日-下午6:52:24
	 * 作者：news
	 */
	List<Seckill> getSeckillList();

	/**
	 * 查询单个秒杀记录
	 * 时间：2018年5月10日-下午6:53:14
	 * 作者：news
	 */
	Seckill getSeckillById(long seckillId);
	
	/**
	 * 秒杀开始时，输出秒杀接口地址，否则输出系统时间和开始时间
	 * 时间：2018年5月10日-下午6:54:43
	 * 作者：news
	 */
	Exposer exportSeckillUrl(long seckillId);
	
	/**
	 * 执行秒杀操作
	 * 时间：2018年5月10日-下午7:04:49
	 * 作者：news
	 */
	SeckillExecution executeSeckill(long seckillId,long userPhone,String md5)
	throws SeckillException,SeckillCloseException,RepeatKillException ;
	
	
	/**
	 * 存储过程
	 * 时间：2018年5月21日-下午8:20:21
	 * 作者：news
	 */
	SeckillExecution executeSeckillProcedure(long seckillId,long userPhone,String md5);

	
	
	
}
