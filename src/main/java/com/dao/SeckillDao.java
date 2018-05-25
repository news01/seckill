package com.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.dto.SeckillExecution;
import com.pojo.Seckill;

public interface SeckillDao {

	/**
	 * 减库存 时间：2018年5月9日-下午11:40:47 作者：news
	 */
	int reduceNumber(@Param("seckillId")long seckillId,@Param("killTime") Date killTime);

	/**
	 * 根据id查询秒杀对象 时间：2018年5月9日-下午11:41:42 作者：news
	 */
	Seckill queryById(long seckillId);

	/**
	 * 根据偏移量查询秒杀商品列表 时间：2018年5月9日-下午11:42:41 作者：news
	 */
	List<Seckill> queryAll(@Param("offet") int offet, @Param("limit") int limit);
	
	/**
	 * 存储过程
	 * 时间：2018年5月21日-下午8:20:21
	 * 作者：news
	 */
	SeckillExecution executeSeckillProcedure(Map<String, Object> params);

}
