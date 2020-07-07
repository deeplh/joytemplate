package com.keepjoy.core.util;

import org.apache.commons.lang.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;



/**
 *
 */
public final class DateUtil {
	//	private static final Log log=LogFactory.getLog(DateUtil.class);

	private DateUtil(){
	}
	
	public static final String DATEFORMAT_SS = "ss";

	public static final String DATEFORMAT_MM_MINUTE = "mm";

	public static final String DATEFORMAT_HH = "HH";

	public static final String DATEFORMAT_HHMM = "HHmm";

	public static final String DATEFORMAT_HH_MM = "HH:mm";

	public static final String DATEFORMAT_MMSS = "mmss";

	public static final String DATEFORMAT_MM_SS = "mm:ss";

	public static final String DATEFORMAT_HHMMSS = "HHmmss";

	public static final String DATEFORMAT_HH_MM_SS = "HH:mm:ss";

	public static final String DATEFORMAT_YYYY = "yyyy";

	public static final String DATEFORMAT_MM_MONTH = "MM";

	public static final String DATEFORMAT_DD = "dd";

	public static final String DATEFORMAT_YYYYMM = "yyyyMM";

	public static final String DATEFORMAT_YYYY_MM = "yyyy-MM";

	public static final String DATEFORMAT_MMDD = "MMdd";

	public static final String DATEFORMAT_MM_DD = "MM-dd";

	public static final String DATEFORMAT_YYYYMMDD = "yyyyMMdd";

	public static final String DATEFORMAT_YYYY_MM_DD = "yyyy-MM-dd";

	public static final String DATEFORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

	public static final String DATEFORMAT_YYYYMMDDHHMMSSSSSS = "yyyyMMddHHmmss";

	public static final String DATEFORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";


	public static List<String> getDateFormatList(){
		List<String> dateFormatList=new ArrayList<String>();
		dateFormatList.add(DateUtil.DATEFORMAT_YYYY_MM_DD);
		dateFormatList.add(DateUtil.DATEFORMAT_YYYY_MM_DD_HH_MM_SS);
		dateFormatList.add(DateUtil.DATEFORMAT_YYYYMMDD);
		dateFormatList.add(DateUtil.DATEFORMAT_YYYYMMDDHHMMSS);
		return dateFormatList;
	}

	public static Date dateToDateByDateFormat(Date date,String srcFormat,String newFormat) throws ParseException{
		return DateUtil.stringToDateByDateFormat(DateUtil.dateToStringByDateFormat(date,srcFormat),newFormat);
	}

	public static String stringToStringByDateFormat(String dateStr,String srcFormat,String newFormat) throws ParseException{
		return DateUtil.dateToStringByDateFormat(DateUtil.stringToDateByDateFormat(dateStr,srcFormat),newFormat);
	}
	/**
	 * 将date转换为对应格式的String
	 *
	 * @param date
	 * @return
	 */
	public static String dateToStringByDateFormat(Date date) {
		for(String dateFormat:getDateFormatList()){
			String dateStr=dateToStringByDateFormat(date, dateFormat);
			if(StringUtils.isNotEmpty(dateStr)){
				return dateStr;
			}
		}
		return null;
	}

	/**
	 * 将date转换为对应格式的String
	 *
	 * @param date
	 * @param dateFormat
	 * @return
	 */
	public static String dateToStringByDateFormat(Date date,String dateFormat) {
		//		try {
		if(null==date)return null;
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(date);
		//		} catch (Exception e) {
		//			MyLogUtil.printlnException(log, e, "转换日期时出现未知异常:");
		//			return null;
		//		}
	}


	/**
	 * 将String转换成相应格式的date
	 * @param date
	 * @return
	 * @throws ParseException 
	 */
	public static Date stringToDateByDateFormat(String dateStr) throws ParseException {
		for(String dateFormat:getDateFormatList()){
			if(dateFormat.length()!=dateStr.length()){
				continue;
			}
			Date date=stringToDateByDateFormat(dateStr, dateFormat);
			if(null!=date){
				return date;
			}
		}
		return null;
	}

	/**
	 * 将String转换成相应格式的date
	 * @param date
	 * @param dateFormat
	 * @return
	 * @throws ParseException 
	 */
	public static Date stringToDateByDateFormat(String date,String dateFormat) throws ParseException {
		//		try {
		if(null==date)return null;
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.parse(date);
		//		} catch (ParseException e) {
		//			MyLogUtil.printlnException(log, e, "转换日期时出现未知异常:");
		//			return null;
		//		}
	}

	/**
	 * date2减date1的时间
	 * @param date1
	 * @param date2
	 * @param dateFormat
	 * @return
	 * @throws Exception
	 */
	public static String date2MinusDate1(Date date1,Date date2,String dateFormat) throws Exception {
		Long time=date2.getTime()-date1.getTime();
		Date date = new Date(time);
		return DateUtil.dateToStringByDateFormat(date,dateFormat);
	}



	/**
	 * 将当前日期的时间加入时分秒，默认是+235959
	 *
	 * @param date
	 * @return
	 */
	public static String addHHmmssToTime(Date date, Long addTime) {
		if(null==date)return null;
		String dateStr = dateToStringByDateFormat(date,DATEFORMAT_YYYYMMDDHHMMSSSSSS);
		//		try {
		if (addTime == null) addTime = 235959l;
		Long dateLong = Long.parseLong(dateStr);
		dateLong = dateLong + addTime;
		return dateLong.toString();
		//		} catch (NumberFormatException e) {
		//			MyLogUtil.printlnException(log, e, "添加日期时出现未知异常:");
		//			return null;
		//		}
	}


	/**
	 * 为用户显示时间 传入参数为string 类型
	 * 支持格式
	 *
	 * @return
	 * @throws ParseException 
	 */
	public static String displayDateTimeForUser(String str) throws ParseException {
		//		try {
		if (str == null) return "";
		Date dt = null;
		SimpleDateFormat format = null;
		if (str.length() == 6) {
			SimpleDateFormat SDF_HHMMSS = new SimpleDateFormat(DATEFORMAT_HHMMSS);
			dt = SDF_HHMMSS.parse(str);
			format = new SimpleDateFormat(DATEFORMAT_HH_MM_SS);
			return format.format(dt);
		} else if (str.length() == 8) {
			SimpleDateFormat SDF_YYYYMMDD = new SimpleDateFormat(DATEFORMAT_YYYYMMDD);
			dt = SDF_YYYYMMDD.parse(str);
			format = new SimpleDateFormat(DATEFORMAT_YYYY_MM_DD);
			return format.format(dt);
		} else if (str.length() == 14) {
			SimpleDateFormat SDF_YYYYMMDDHHMMSS = new SimpleDateFormat("yyyyMMddHHmmss");
			dt = SDF_YYYYMMDDHHMMSS.parse(str);
			format = new SimpleDateFormat(DATEFORMAT_YYYY_MM_DD_HH_MM_SS);
			return format.format(dt);
		} else return str;
		//		} catch (ParseException e) {
		//			MyLogUtil.printlnException(log, e, "显示日期时出现未知异常:");
		//			return null;
		//		}
	}





	/**
	 * 根据field比较日期
	 * @param one
	 * @param two
	 * @param field
	 * @param amount
	 * @return
	 */
	public static boolean compareDate(Date one, Date two,int field, int amount){
		if (one == null
				||two == null)
			return false;
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(two);
		calendar.add(field,amount);
		Date tempDate = calendar.getTime();
		if (one.compareTo(tempDate) > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 如果one 比two 大day天返回true否则返回false
	 *
	 * @param one
	 * @param two
	 * @param day
	 */
	public static boolean compareDate(Date one, Date two, int day) {
		return compareDate(one,two,GregorianCalendar.DAY_OF_MONTH, day);
	}

	/**
	 * 如果one 比two 大minute分钟返回true否则返回false
	 *
	 * @param one
	 * @param two
	 * @param day
	 */
	public static boolean compareDateForMinute(Date one, Date two, int minute) {
		return compareDate(one,two,GregorianCalendar.MINUTE, minute);
	}

	/**
	 * 如果one 比two 大second秒,返回true否则返回false
	 *
	 * @param one
	 * @param two
	 * @param day
	 */
	public static boolean compareDateForSecond(Date one, Date two, int second) {
		return compareDate(one,two,GregorianCalendar.SECOND, second);
	}

	/**
	 * 日期加上对应的时间
	 * @param date
	 * @param field
	 * @param amount
	 * @return
	 */
	public static Date addForDate(Date date,int field, int amount){
		if (date == null) return null;
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(field, amount);
		return calendar.getTime();
	}

	/**
	 * 日期加上天数
	 *
	 * @param date
	 * @param day  天数
	 * @return
	 * @throws Exception
	 */
	public static Date addDayForDate(Date date, int day) {
		return addForDate(date,GregorianCalendar.DAY_OF_MONTH, day);
	}

	/**
	 * 日期加上分钟
	 * @param da
	 * @param minute
	 * @return
	 */
	public static Date addMinuteForDate(Date date, int minute) {
		return addForDate(date,GregorianCalendar.MINUTE, minute);
	}

	/**
	 * 日期加上月份
	 * @param date
	 * @param minute
	 * @return
	 */
	public static Date addMonthForDate(Date date, int month) {
		return addForDate(date,GregorianCalendar.MONTH, month);
	}


	/**
	 * 得到传入date的下个月的第一天
	 *
	 * @param date yyyy-MM-dd
	 * @return yyyy-MM-dd
	 * @throws ParseException 
	 */
	public static String getNextMonthFirstDayFromDate(String date) throws ParseException {
		Calendar cal = Calendar.getInstance();//当前日期
		SimpleDateFormat SDF_YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");
		cal.setTime(SDF_YYYY_MM_DD.parse(date));
		cal.add(Calendar.MONTH, 1);//获得下一个月日期即月份加上一月
		Calendar cal_temp = Calendar.getInstance();//当前日期
		cal_temp.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1);
		return SDF_YYYY_MM_DD.format(cal_temp.getTime());
	}


	/**
	 * 
	 * @param dts
	 * @param amount 正数为后面的,负数为前面的
	 * @return
	 */
	public static Date getDayByAmount(Date dts,int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dts);
		cal.add(Calendar.DATE, amount);
		return cal.getTime();
	}

	public static String getPreMonth() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		Integer month = cal.get(Calendar.MONTH) - 1;
		String monthStr = month.toString();
		if (month < 10) {
			monthStr = "0" + month;
		}
		return monthStr;
	}

	/**
	 * 得到前一年
	 * @return
	 */
	public static String getPreYear() {
		Calendar cal = Calendar.getInstance();
		Integer year = cal.get(Calendar.YEAR) - 1;
		return year.toString();
	}



	public static String translateToHmsByMillisecond(Integer millisecond){
		if(null!=millisecond){
			Integer localSecond=millisecond/1000;
			Integer hour=localSecond/60/60;
			Integer minute=(localSecond-hour*60*60)/60;
			Integer second=localSecond-minute*60-hour*60*60;
			return hour+"h"+minute+"m"+second+"s";
		}else{
			return null;
		}
	}


	/**
	 * 获取某日期区间的所有日期  日期倒序
	 *
	 * @param startDate  开始日期
	 * @param endDate    结束日期
	 * @return 区间内所有日期
	 * @throws ParseException 
	 */
	public static List<Date> getPerDatesByStartAndEndDate(Date startDate, Date endDate) throws ParseException {
		long start = startDate.getTime();
		long end = endDate.getTime();
		if (start > end) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(endDate);
		List<Date> res = new ArrayList<Date>();
		while (end >= start) {
			res.add(calendar.getTime());
			calendar.add(Calendar.DAY_OF_MONTH, -1);
			end = calendar.getTimeInMillis();
		}
		return res;

	}
	
	public static List<String> getPerDatesByStartAndEndDate(String startDate, String endDate, String dateFormat) throws ParseException {
		DateFormat format = new SimpleDateFormat(dateFormat);
		Date sDate = format.parse(startDate);
		Date eDate = format.parse(endDate);
		long start = sDate.getTime();
		long end = eDate.getTime();
		if (start > end) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(eDate);
		List<String> res = new ArrayList<String>();
		while (end >= start) {
			res.add(format.format(calendar.getTime()));
			calendar.add(Calendar.DAY_OF_MONTH, -1);
			end = calendar.getTimeInMillis();
		}
		return res;
	}
	/**
	 * ********************test
	 * @throws ParseException ***************
	 */

	public static void main(String args[]) throws ParseException {
//		for(Date s:getPerDatesByStartAndEndDate(DateUtils.addDays(new Date(), -2),new Date())){
//			System.out.println(s);
//		}
//		
//		for(String s:getPerDatesByStartAndEndDate("20180422","20180424",DateUtil.DATEFORMAT_YYYYMMDD)){
//			System.out.println(s);
//		}
	}
}
