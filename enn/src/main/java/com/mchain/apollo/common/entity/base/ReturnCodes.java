package com.mchain.apollo.common.entity.base;

public class ReturnCodes {
	/**
	 * 成功
	 */
	public final static String SUCCESS = "000";
	/**
	 * 失败
	 */
	public final static String FAIL = "111";
	/**
	 * 未认证
	 */
	public final static String UNAUTHENTICATED = "401";
	/**
	 * 未授权
	 */
	public final static String UNAUTHORIZED = "403";
	/**
	 * 解析错误
	 */
	public final static String MSG_PARSE_ERR = "700";
	/**
	 * 未知错误
	 */
	public final static String UNKNOW_ERR = "999";
}
