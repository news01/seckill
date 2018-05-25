package com.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.dao.SeckillDao;
import com.dao.SuccessKillDAO;
import com.dao.catche.RedisDao;
import com.dto.Exposer;
import com.dto.SeckillExecution;
import com.exception.RepeatKillException;
import com.exception.SeckillCloseException;
import com.exception.SeckillException;
import com.pojo.Seckill;
import com.pojo.SuccessKilled;
import com.seckillStateEnum.SeckillStateEnum;
import com.service.SeckillService;

@Service
public class SeckillServiceImpl implements SeckillService {

	private Logger logger = Logger.getLogger(SeckillServiceImpl.class);

	@Autowired
	private SeckillDao seckillDao;
	@Autowired
	private SuccessKillDAO successKillDao;
	@Autowired
	private RedisDao redisDao;

	private final String flag = "cjv'pazufgawmcv;lzn oadhfvpsjde";

	@Override
	public List<Seckill> getSeckillList() {
		return seckillDao.queryAll(0, 4);
	}

	@Override
	public Seckill getSeckillById(long seckillId) {
		return seckillDao.queryById(seckillId);
	}

	@Override
	public Exposer exportSeckillUrl(long seckillId) {
		Seckill seckill = redisDao.getSeckill(seckillId);
		if (seckill == null) {
			seckill = seckillDao.queryById(seckillId);
			if (seckill == null) {
				return new Exposer(false, seckillId);
			} else {
				redisDao.putSeckill(seckill);
			}

		}
		// Seckill seckill = seckillDao.queryById(seckillId);
		/*
		 * if (seckill == null) { return new Exposer(false, seckillId); }
		 */
		Date startTime = seckill.getStartTime();
		Date endTime = seckill.getEndTime();
		Date currentTime = new Date();
		if (currentTime.getTime() < startTime.getTime() || currentTime.getTime() > endTime.getTime()) {
			return new Exposer(false, seckillId, currentTime.getTime(), startTime.getTime(), endTime.getTime());
		}

		String md5 = getMD5(seckillId);
		return new Exposer(true, md5, seckillId);
	}

	/**
	 * md5加密 时间：2018年5月10日-下午7:44:31 作者：news
	 */
	private String getMD5(long md5) {
		String base = md5 + "/" + flag;
		String newMd5 = DigestUtils.md5DigestAsHex(base.getBytes());
		return newMd5;

	}

	/**
	 * 执行秒杀
	 */

	/**
	 * 使用注解控制事物的优点： 1.开发团队达成一致约定，明确标注事务方法的编程风格 2.保证事务方法的执行时间尽可能短，不要穿插其他网络操作
	 * 3.不是所有的方法都需要事务
	 */
	@Override
	@Transactional
	public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
			throws SeckillException, SeckillCloseException, RepeatKillException {

		// System.out.println(seckillId + "," + userPhone + "," + md5 + "," +
		// getMD5(seckillId));

		if (md5 == null || !md5.equals(getMD5(seckillId))) {
			throw new SeckillException("seckill data rewire");
		}
		Date currentTime = new Date();
		try {
			// 记录购买行为
			int insertResu = successKillDao.insertSuccessKilled(seckillId, userPhone, 0);
			if (insertResu <= 0) {
				// 重复秒杀
				// System.out.println("seckill repeat》》重复提交");
				throw new RepeatKillException("seckill repeat");
			} else {
				// 执行秒杀，减库存 + 记录购买行为
				int resu = seckillDao.reduceNumber(seckillId, currentTime);
				if (resu < 0) {
					throw new SeckillCloseException("seckill closed");
				} else {
					// 秒杀成功
					// System.out.println("秒杀成功");
					SuccessKilled successKilled = successKillDao.queryByIdWithSeckill(seckillId, userPhone);
					return new SeckillExecution(seckillId, SeckillStateEnum.SUCCESS, successKilled);
				}

			}

		} catch (RepeatKillException e) {
			logger.error("重复提交", e);
			throw new RepeatKillException("重复提交");
		} catch (SeckillCloseException al) {
			logger.error("seckill closed", al);

		} catch (SeckillException ew) {
			logger.error("seckill data rewire", ew);
		} catch (Exception e) {
			logger.error("秒杀出现异常", e);
			throw new SeckillException("秒杀出异常");
		}
		return null;

	}

	/**
	 * 存储过程执行秒杀
	 */
	@Override
	public SeckillExecution executeSeckillProcedure(long seckillId, long userPhone, String md5) {
		if(md5 == null || !md5.equals(getMD5(seckillId))){
			return new SeckillExecution(seckillId, SeckillStateEnum.DATA_REWRITE);
		}
		Map<String, Object> map = new HashMap<String,Object>();
		Date date = new Date();
		map.put("seckillId", seckillId);
		map.put("phone", userPhone);
		map.put("killTime", date);
		
		SeckillExecution seckillExecution = seckillDao.executeSeckillProcedure(map);
		try {
			//没有为-2
			int result = MapUtils.getInteger(map, "result",-2);
			if(result==1){
				SuccessKilled successKilled = successKillDao.queryByIdWithSeckill(seckillId, userPhone);
				return new SeckillExecution(seckillId, SeckillStateEnum.SUCCESS,successKilled);
			}
			else{
				return new SeckillExecution(seckillId, SeckillStateEnum.getName(result));
			}
			
		} catch (Exception e) {
			return new SeckillExecution(seckillId, SeckillStateEnum.INSERT_ERROR);
		}
		
	}

}
