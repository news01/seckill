package com.test;

import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * jedisDemo
 * 
 * @author Administrator
 *
 */
public class JedisDemo_1 {

	private final static String IP = "192.168.0.102";
	private final static Integer PORT = 6379;
	Jedis jedis = null;

	@Before
	public void defore() {
		jedis = new Jedis(IP, PORT);
	}

	@Test
	public void testJedis() {
		jedis.set("name", "news");
		String name = jedis.get("name");
		System.out.println("name:" + name);
		jedis.close();
	}

	@Test
	public void testJedis_2() {
		// 获取连接池连接对象
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		// 设置最大连接数
		jedisPoolConfig.setMaxTotal(30);
		// 设置最大空闲连接
		jedisPoolConfig.setMaxIdle(10);

		// 获得连接池
		JedisPool jedisPool = new JedisPool(jedisPoolConfig, IP, PORT);
		// 获得核心对象
		Jedis jedis = null;

		try {
			// 通过连接池获得连接
			jedis = jedisPool.getResource();
			jedis.set("name", "牛施");
			String name = jedis.get("name");
			System.out.println("通过连接池获得的值：" + name);
		} catch (Exception e) {
			System.out.println("通过连接池获得值时出错了！！" + e.getMessage());
		} finally {
			if (jedis != null) {
				jedis.close();
			}
			if (jedisPool != null) {
				jedisPool.close();
			}
		}

	}

	public static void main(String[] args) {
		Jedis jedis = new Jedis(IP, PORT);
		jedis.set("news", "news");
		String name = jedis.get("news");
		System.out.println("name:" + name);
		jedis.close();

	}
}
