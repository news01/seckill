package com.dao.catche;

import org.springframework.stereotype.Component;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.pojo.Seckill;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * redis数据访问
 * 
 * @author Administrator
 *
 */
@Component
public class RedisDao {

	private final JedisPool jedisPool;

	public RedisDao(String ip, int port) {
		jedisPool = new JedisPool(ip, port);
	}

	/**
	 * 序列化 protostuup
	 */
	private RuntimeSchema<Seckill> runtimeSchema = RuntimeSchema.createFrom(Seckill.class);

	public Seckill getSeckill(long seckillId) {
		Jedis jedis = null;
		String key = "seckill:" + seckillId;
		try {
			jedis = jedisPool.getResource();
			byte[] bs = jedis.get(key.getBytes());
			if (bs != null) {
				/**
				 * 空对象
				 */
				Seckill seckill = runtimeSchema.newMessage();
				ProtostuffIOUtil.mergeFrom(bs, seckill, runtimeSchema);
				return seckill;
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}

		return null;

	}

	/**
	 * 将对象序列化 时间：2018年5月15日-下午7:04:40 作者：news
	 */
	public String putSeckill(Seckill seckill) {
		Jedis jedis = jedisPool.getResource();
		String key = "seckill:" + seckill.getSeckillId();
		try {
			final int timeout = 60 * 60;// 秒
			byte[] bs = ProtostuffIOUtil.toByteArray(seckill, runtimeSchema,
					LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
			String result = jedis.setex(key.getBytes(), timeout, bs);
			return result;
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}

	}
}
