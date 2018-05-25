package com.exception;

/**
 * 重复秒杀异常（运行时异常）
 * @author Administrator
 *
 */
public class RepeatKillException extends SeckillException {

	public RepeatKillException(String message) {
		super(message);
	}
//	spring的声明式事务只能识别运行时异常

	public RepeatKillException(String message, Throwable cause) {
		super(message, cause);
	}
	
	
	
	
}
