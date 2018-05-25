package com.pojo;

import java.util.Date;

public class SuccessKilled {
	/**
	 * 秒杀商品id
	 */
	private Long seckillId;

	/**
	 * 用户手机号
	 */
	private Long userPhone;

	/**
	 * 状态提示 -1：无效；0：成功 1：已付款
	 */
	private Byte state;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 多对一实体
	 */
	private Seckill seckill;

	/**
	 * 获取秒杀商品id
	 *
	 * @return seckill_id - 秒杀商品id
	 */
	public Long getSeckillId() {
		return seckillId;
	}

	/**
	 * 设置秒杀商品id
	 *
	 * @param seckillId
	 *            秒杀商品id
	 */
	public void setSeckillId(Long seckillId) {
		this.seckillId = seckillId;
	}

	/**
	 * 获取用户手机号
	 *
	 * @return user_phone - 用户手机号
	 */
	public Long getUserPhone() {
		return userPhone;
	}

	/**
	 * 设置用户手机号
	 *
	 * @param userPhone
	 *            用户手机号
	 */
	public void setUserPhone(Long userPhone) {
		this.userPhone = userPhone;
	}

	/**
	 * 获取状态提示 -1：无效；0：成功 1：已付款
	 *
	 * @return state - 状态提示 -1：无效；0：成功 1：已付款
	 */
	public Byte getState() {
		return state;
	}

	/**
	 * 设置状态提示 -1：无效；0：成功 1：已付款
	 *
	 * @param state
	 *            状态提示 -1：无效；0：成功 1：已付款
	 */
	public void setState(Byte state) {
		this.state = state;
	}

	/**
	 * 获取创建时间
	 *
	 * @return create_time - 创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置创建时间
	 *
	 * @param createTime
	 *            创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Seckill getSeckill() {
		return seckill;
	}

	public void setSeckill(Seckill seckill) {
		this.seckill = seckill;
	}

	@Override
	public String toString() {
		return "SuccessKilled [seckillId=" + seckillId + ", userPhone=" + userPhone + ", state=" + state
				+ ", createTime=" + createTime + ", seckill=" + seckill + "]";
	}

}