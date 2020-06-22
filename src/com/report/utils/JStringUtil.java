package com.report.utils;

import java.util.Calendar;
import java.util.UUID;

public class JStringUtil {
	
	/**
	 * 获取随机32位ID
	 * @return
	 */
	public static String creatId(){
		UUID uuid = UUID.randomUUID();
		String id = uuid.toString().replace("-", "");
		return id;
	}
	
	/**
	 * 判断是否为空
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		return str==null || "".equals(str);
	}
	
	/**
	 * 获取当前日期 格式为（yyyy-MM-dd hh:mm:ss）
	 * @return
	 */
	public static String getNowDate(){
		 Calendar calendar = Calendar.getInstance();
	     String NowYear = Integer.toString(calendar.get(Calendar.YEAR));
	     String NowMonth = Integer.toString(calendar.get(Calendar.MONTH) + 1);
	     String NowDay = Integer.toString(calendar.get(Calendar.DATE));
	     String NowHour = Integer.toString(calendar.get(Calendar.HOUR_OF_DAY));
	     String NowMinute = Integer.toString(calendar.get(Calendar.MINUTE));
	     String NowSecond = Integer.toString(calendar.get(Calendar.SECOND));
	     String NowDate = NowYear + "-" + ((NowMonth.length() == 1) ? "0" + NowMonth : NowMonth) + "-" + ((NowDay.length() == 1) ? "0" + NowDay : NowDay) + " " + ((NowHour.length() == 1) ? "0" + NowHour : NowHour) + ":" + ((NowMinute.length() == 1) ? "0" + NowMinute : NowMinute) + ":" + ((NowSecond.length() == 1) ? "0" + NowSecond : NowSecond);
	     return NowDate;
	}
	
}

