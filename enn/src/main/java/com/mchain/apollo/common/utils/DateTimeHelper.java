package com.mchain.apollo.common.utils;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTimeHelper {
	/**
	 * 
	 * @Title: getCurrentUTCDatetime
	 * @Description: 获取当前UTC时间
	 * @return
	 * @return: Date
	 */
	public static Date getCurrentUTCDateTime(){
		ZonedDateTime utc = ZonedDateTime.now(ZoneOffset.UTC);
		return Date.from(utc.toInstant());
	}
	
	/**
	 * 
	 * @Title: getFmtCurrentUTCDateTime
	 * @Description: 获取当前UTC时间，格式化为 yyyy-MM-dd hh:mm:ss
	 * @return
	 * @return: String
	 */
	public static String getFmtCurrentUTCDateTime() {
		ZonedDateTime utc = ZonedDateTime.now(ZoneOffset.UTC);
		return utc.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
	}
}
