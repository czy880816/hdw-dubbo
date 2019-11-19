package com.hdw.utils;

/**
 * 自定义异常
 * 
 * @Author TuMinglong
 * @email tuminglong@126.com
 * @Date  2018/12/21 15:14
 */
public class BaseException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
    private String msg;
    private int code = 500;
    
    public BaseException(String msg) {
		super(msg);
		this.msg = msg;
	}
	
	public BaseException(String msg, Throwable e) {
		super(msg, e);
		this.msg = msg;
	}
	
	public BaseException(String msg, int code) {
		super(msg);
		this.msg = msg;
		this.code = code;
	}
	
	public BaseException(String msg, int code, Throwable e) {
		super(msg, e);
		this.msg = msg;
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	
}
