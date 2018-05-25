package com.dao;

import org.apache.ibatis.annotations.Param;

import com.pojo.SuccessKilled;

public interface SuccessKillDAO {

	/**
	 * 插入购买明细，可过滤重复 时间：2018年5月9日-下午11:46:02 作者：news
	 */
	int insertSuccessKilled(@Param("seckillId")long seckillId, @Param("userPhone")long userPhone,@Param("state")int state);

	/**
	 * 根据id查询successKilled并携带秒杀产品对象实例 时间：2018年5月9日-下午11:48:36 作者：news
	 */
	SuccessKilled queryByIdWithSeckill(@Param("seckillId")long seckillId,@Param("userPhone")long userPhone);

}
