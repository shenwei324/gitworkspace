package com.alan.shenw.commons.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateKit {

	/**
	 * 通过年月日构造一个Date实例，时、分、秒、毫秒都为0
	 * @param year 1970 - XXXX
	 * @param month 1-12
	 * @param day 1-31
	 */
	public static Date get(int year, int month, int day) {
		Calendar cale = Calendar.getInstance();
		cale.set(year, month - 1, day, 0, 0, 0);
		cale.set(Calendar.MILLISECOND, 0);
		return cale.getTime();
	}

	public static Date parse(String date, String... format) {
		Date result = null;
		if (date != null && format != null && format.length > 0) {
			SimpleDateFormat sdf = new SimpleDateFormat();
			for (String fmt : format) {
				sdf.applyPattern(fmt);
				try {
					result = sdf.parse(date);
					break;
				} catch (ParseException e) {
					continue;
				}
			}
		}
		return result;
	}
	
	public static String reformat(String date, String fmtFrom, String fmtTo) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(fmtFrom);
		Date d = sdf.parse(date);
		sdf.applyPattern(fmtTo);
		return sdf.format(d);
	}
}
