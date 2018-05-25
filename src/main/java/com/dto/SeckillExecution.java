package com.dto;

import com.pojo.SuccessKilled;
import com.seckillStateEnum.SeckillStateEnum;

/**
 * 秒杀执行后结果
 * @author Administrator
 *
 */
public class SeckillExecution {

	private long seckillId;

//	秒杀执行状态
	private int state;
	
//	秒杀成功对象
	private SuccessKilled successKilled;
	
//	状态描述
	private String stateInfo;

	public long getSeckillId() {
		return seckillId;
	}

	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public SuccessKilled getSuccessKilled() {
		return successKilled;
	}

	public void setSuccessKilled(SuccessKilled successKilled) {
		this.successKilled = successKilled;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public SeckillExecution(long seckillId, int state, SuccessKilled successKilled) {
		super();
		this.seckillId = seckillId;
//		this.state = state;
		this.successKilled = successKilled;
	}

	public SeckillExecution(SuccessKilled successKilled) {
		super();
		this.successKilled = successKilled;
	}

	public SeckillExecution(long seckillId) {
		super();
		this.seckillId = seckillId;
	}

	public SeckillExecution(long seckillId, SeckillStateEnum stateEunm, SuccessKilled successKilled) {
		super();
		this.seckillId = seckillId;
		this.state = stateEunm.getState();
		this.stateInfo = stateEunm.getStateInfo();
		this.successKilled = successKilled;
	}

	public SeckillExecution(long seckillId, SeckillStateEnum stateEunm) {
		super();
		this.seckillId = seckillId;
		this.stateInfo = stateEunm.getStateInfo();
	}

	@Override
	public String toString() {
		return "SeckillExecution [seckillId=" + seckillId + ", state=" + state + ", successKilled=" + successKilled
				+ ", stateInfo=" + stateInfo + "]";
	}
	
	
	
	
	
}
