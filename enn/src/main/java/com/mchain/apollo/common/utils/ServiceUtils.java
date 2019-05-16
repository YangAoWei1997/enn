package com.mchain.apollo.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.enn.commodity.synergistic.entity.Arrangement;
import com.enn.commodity.synergistic.entity.Group;
import com.mchain.apollo.common.entity.base.Data;
import com.mchain.apollo.common.entity.base.FormatType;

/**
 * 系统工具类
 * @ClassName: ServiceUtils.java
 * @Description: TODO
 * @author: RenXiaoQing
 * @param <T>
 * @Date: 2019年2月13日 下午5:37:29
 */
public class ServiceUtils {
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString();
		return uuid.replaceAll("-", "");
		
	}
	public static String getDateUUID() {
		Date date = new Date();
		//设置要获取到什么样的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        //获取String类型的时间
        String createdate = sdf.format(date);
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if (hashCodeV < 0) {//有可能是负数
            hashCodeV = -hashCodeV;
        }
        return createdate + String.format("%6d", hashCodeV);
	}
	
	/**
	 * 获取业务对象
	 * 
	 * @Title: getInstance
	 * @Description: TODO
	 * @param datas
	 * @return
	 * @return: Complain
	 */
	public static<T> Object getInstance(List<Data> datas,Class<T> clazz) {
		Object object = new Object();
		try {
			for (Data data : datas) {
				if (clazz.getSimpleName().equals(data.getDataType())) {
					if (FormatType.STRING.equals(data.getFormatType())) {
						object = JacksonUtil.getInstance().map2pojo((Map) data.getContent(), clazz);
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return object;
	}
}
