package util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

// GetSystemTime
public class DateUtil {
 
 
	public static final int YEAR = 1;
 
	public static final int YEARMONTH = 2;
 
	public static final int YEARMONTHDAY = 3;
 
	public static final int YMD_HOUR = 4;
 
	public static final int YMD_HOURMINUTE = 5;
 
	public static final int FULL = 6;
 
	public static final int UTILTIME = 7;

	public static Date addDays(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + days);
		return calendar.getTime();
	}

	//Get the current time depending on the type of specified time format
	public static synchronized String getCurrentTime(int type) {
		String format = getFormat(type);
		SimpleDateFormat timeformat = new SimpleDateFormat(format);
		Date date = new Date();
		return timeformat.format(date);
	}

	//Returns the current system time (date) (month) (year)
	public static synchronized String getCurrentTime() {
		SimpleDateFormat timeformat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return timeformat.format(date);
	}

	//According to the parameter format, format the current date
	public static synchronized String getDateString(String format) {
		SimpleDateFormat timeformat = new SimpleDateFormat(format);
		Date date = new Date();
		return timeformat.format(date);
	}

	//According to the specified time format type, format the time format
	private static String getFormat(int type) {
		String format = "";
		if (type == 1) {
			format = "yyyy";
		} else if (type == 2) {
			format = "yyyy-MM";
		} else if (type == 3) {
			format = "yyyy-MM-dd";
		} else if (type == 4) {
			format = "yyyy-MM-dd HH";
		} else if (type == 5) {
			format = "yyyy-MM-dd HH:mm";
		} else if (type == 6) {
			format = "yyyy-MM-dd HH:mm:ss";
		} else if (type == 7) {
			format = "yyyyMMddHHmmss";
		} else {
			throw new RuntimeException("Date format parameter error");
		}
		return format;
	}

	public static int getYear(String dateString) {
		SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = dd.parse(dateString);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			return cal.get(Calendar.YEAR);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static int getMonth(String dateString) {
		SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = dd.parse(dateString);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			return cal.get(Calendar.MONTH) + 1;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static int getDay(String dateString) {
		SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = dd.parse(dateString);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			return cal.get(Calendar.DAY_OF_MONTH);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static Date StringToDate(String dateStr, String formatStr) {
		SimpleDateFormat dd = new SimpleDateFormat(formatStr);
		Date date = null;
		try {
			date = dd.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	
	public static double getHours(String date) {
		SimpleDateFormat timeformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date d = new Date();
			Date d1 = timeformat.parse(date);

			long temp = d.getTime() - d1.getTime();
			double f = temp / 3600000d;
			BigDecimal b = new BigDecimal(f);
			double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			return f1;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public static int getMonth(Date start, Date end) {
		if (start.after(end)) {
			Date t = start;
			start = end;
			end = t;
		}
		Calendar startCalendar = Calendar.getInstance();
		startCalendar.setTime(start);
		Calendar endCalendar = Calendar.getInstance();
		endCalendar.setTime(end);
		Calendar temp = Calendar.getInstance();
		temp.setTime(end);
		temp.add(Calendar.DATE, 1);

		int year = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
		int month = endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);

		if ((startCalendar.get(Calendar.DATE) == 1) && (temp.get(Calendar.DATE) == 1)) {
			return year * 12 + month + 1;
		} else if ((startCalendar.get(Calendar.DATE) != 1) && (temp.get(Calendar.DATE) == 1)) {
			return year * 12 + month;
		} else if ((startCalendar.get(Calendar.DATE) == 1) && (temp.get(Calendar.DATE) != 1)) {
			return year * 12 + month;
		} else {
			return (year * 12 + month - 1) < 0 ? 0 : (year * 12 + month);
		}
	}

	public static int getAge(String birthDayString) {
		SimpleDateFormat timeformat = new SimpleDateFormat("yyyy-MM-dd");
		Date birthDay = null;
		try {
			birthDay = timeformat.parse(birthDayString);
		} catch (ParseException e) {
			return 0;
		}
		Calendar cal = Calendar.getInstance();

		if (cal.before(birthDay)) {
			return 0;
		}

		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
		cal.setTime(birthDay);

		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH);
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

		int age = yearNow - yearBirth;

		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				// monthNow==monthBirth
				if (dayOfMonthNow < dayOfMonthBirth) {
					age--;
				} else {
				}
			} else {
				// monthNow>monthBirth
				age--;
			}
		} else {
			// monthNow<monthBirth
			// donothing
		}

		return age;
	}

//Calculated by the number of days between two dates
	
	public static int daysBetween(Date smdate, Date bdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		smdate = sdf.parse(sdf.format(smdate));
		bdate = sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	 
	public static int daysBetween(String smdate, String bdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(smdate));
		long time1 = cal.getTimeInMillis();
		cal.setTime(sdf.parse(bdate));
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	public static void main(String a[]) {
		try {
			int aa = getYear("2012-01-08");
			System.out.println(aa);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
