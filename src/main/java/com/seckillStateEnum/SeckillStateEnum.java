package com.seckillStateEnum;

public enum SeckillStateEnum {

	SUCCESS(1, "秒杀成功"), END(0, "秒杀结束"), REPEAT_KILL(-1, "重复秒杀"), INSERT_ERROR(-2, "系统异常"), DATA_REWRITE(-3, "数据篡改");
	// 成员变量
	private int state;
	private String stateInfo;

	// 构造方法
	private SeckillStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	// 普通方法
	public static SeckillStateEnum getName(int index) {
		for (SeckillStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}

	public static SeckillStateEnum stateOf(int index) {
		for (SeckillStateEnum seckillStateEnum : values()) {
			if (seckillStateEnum.getState() == index) {
				return seckillStateEnum;
			}
		}
		return null;
	}

	// get set 方法

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}
}