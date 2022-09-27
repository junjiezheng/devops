package com.cherrypicks.devops.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;

/**
 * 日期转换工具
 */
public class DateUtil {

  public static final String DATE_DIVISION = "-";
  public static final String DATE_TIME_PATTERN_DEFAULT = "yyyy-MM-dd HH:mm:ss";
  public static final String DATE_MS_TIME_PATTERN_DEFAULT = "yyyy-MM-dd HH:mm:ss.SSS";
  public static final String DATE_PATTERN_DEFAULT = "yyyy-MM-dd";
  public static final String DATE_PATTERN_YYYYMMDD_HH_MM = "yyyy-MM-dd HH:mm";
  public static final String DATA_PATTERN_YYYYMMDD = "yyyyMMdd";
  public static final String TIME_PATTERN_HHMMSS = "HH:mm:ss";
  public static final String TIME_PATTERN_HHMM = "HH:mm";
  public static final String DATA_PATTERN_YYYYMMDD_T_HH_MM_SS_XXX = "yyyy-MM-dd'T'HH:mm:ssXXX";

  public static final int ONE_SECOND = 1000;
  public static final int ONE_MINUTE = 60 * ONE_SECOND;
  public static final int ONE_HOUR = 60 * ONE_MINUTE;
  public static final long ONE_DAY = 24L * ONE_HOUR;

  public static final String DATE_SECOND = "ss";
  public static final String DATE_MINUTES = "mi";
  public static final String DATE_HOUR = "hr";
  public static final String DATE_DAY = "dd";
  public static final String DATE_MONTH = "mm";
  public static final String DATE_YEAR = "yy";

  public static final String TIME_LANGUGE_EN = "en";
  public static final String TIME_LANGUGE_HK = "zh-hk";
  public static final String TIME_LANGUGE_CN = "zh-cn";
  public static final String TIME_LANGUGE_UR = "ur";
  public static final String TIME_LANGUGE_ID = "id";


  private DateUtil() {

  }

  /**
   * Return the current date
   *
   * @return － DATE<br>
   */
  public static Date getCurrentDate() {
    Calendar cal = Calendar.getInstance();
    return cal.getTime();
  }

  /**
   * Return the current date string
   *
   * @return － 产生的日期字符串<br>
   */
  public static String getCurrentDateStr() {
    Calendar cal = Calendar.getInstance();
    Date currDate = cal.getTime();

    return format(currDate);
  }

  /**
   * Return the current date in the specified format
   *
   * @param strFormat
   * @return
   */
  public static String getCurrentDateStr(String strFormat) {
    Calendar cal = Calendar.getInstance();
    Date currDate = cal.getTime();

    return format(currDate, strFormat);
  }

  public static Date getTime(long millis) {
    Calendar cal = Calendar.getInstance();
    if (millis > 0) {
      cal.setTimeInMillis(millis);
    }
    return cal.getTime();
  }

  /**
   * Parse a string and return a date value
   *
   * @param dateValue
   * @return
   * @throws Exception
   */
  public static Date parseDate(String dateValue) {
    return parseDate(DATE_PATTERN_DEFAULT, dateValue);
  }

  /**
   * Parse a strign and return a datetime value
   *
   * @param dateValue
   * @return format(yyyy - MM - dd HH : mm : ss)
   */
  public static Date parseDateTime(String dateValue) {
    return parseDate(DATE_TIME_PATTERN_DEFAULT, dateValue);
  }

  /**
   * Parse a string and return the date value in the specified format
   *
   * @param strFormat
   * @param dateValue
   * @return
   * @throws ParseException
   * @throws Exception
   */
  public static Date parseDate(String strFormat, String dateValue) {
		if (dateValue == null) {
			return null;
		}

		if (strFormat == null) {
			strFormat = DATE_TIME_PATTERN_DEFAULT;
		}

    SimpleDateFormat dateFormat = new SimpleDateFormat(strFormat);
    Date newDate = null;

    try {
      newDate = dateFormat.parse(dateValue);

    } catch (ParseException pe) {
      newDate = null;
    }
    return newDate;
  }

  /**
   * 将Timestamp类型的日期转换为系统参数定义的格式的字符串。
   *
   * @param aTs_Datetime 需要转换的日期。
   * @return 转换后符合给定格式的日期字符串
   */
  public static String format(Date aTsDatetime) {
    return format(aTsDatetime, DATE_PATTERN_DEFAULT);
  }

  /**
   * 将Timestamp类型的日期转换为系统参数定义的格式的字符串。
   *
   * @param aTs_Datetime 需要转换的日期。
   * @return 转换后符合给定格式的日期字符串
   */
  public static String formatTime(Date aTsDatetime) {
    return format(aTsDatetime, DATE_TIME_PATTERN_DEFAULT);
  }

  /**
   * 将Date类型的日期转换为系统参数定义的格式的字符串。
   *
   * @param aTs_Datetime
   * @param as_Pattern
   * @return
   */
  public static String format(Date aTsDatetime, String asPattern) {
		if (aTsDatetime == null || asPattern == null) {
			return null;
		}
    SimpleDateFormat dateFromat = new SimpleDateFormat();
    dateFromat.applyPattern(asPattern);

    return dateFromat.format(aTsDatetime);
  }

  /**
   * 转换本地时间为long 转换时区
   *
   * @param aTs_Datetime
   * @param as_Pattern
   * @param timeZone     example:"Asia/Hong_Kong"
   * @return xxxxx
   */
  public static String formatZoneDateString(Long millis, String asPattern, String timeZone) {
    Date date = getTime(millis);
    SimpleDateFormat dateFromat = new SimpleDateFormat();
    if (StringUtils.isNotBlank(timeZone)) {
      dateFromat.applyPattern(asPattern);
      dateFromat.setTimeZone(TimeZone.getTimeZone(timeZone));

    }
    return dateFromat.format(date);

  }


  public static String getFormatTime(Date dateTime) {
    return format(dateTime, TIME_PATTERN_HHMMSS);
  }

  /**
   * @param aTs_Datetime
   * @param as_Pattern
   * @return
   */
  public static String format(Timestamp aTsDatetime, String asPattern) {
		if (aTsDatetime == null || asPattern == null) {
			return null;
		}
    SimpleDateFormat dateFromat = new SimpleDateFormat();
    dateFromat.applyPattern(asPattern);

    return dateFromat.format(aTsDatetime);
  }

  /**
   * 取得指定日期N天后的日期
   *
   * @param date
   * @param days
   * @return
   */
  public static Date addDays(Date date, int days) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);

    cal.add(Calendar.DAY_OF_MONTH, days);

    return cal.getTime();
  }

  /**
   * 计算两个日期之间相差的天数
   *
   * @param date1
   * @param date2
   * @return
   */
  public static int daysBetween(Date date1, Date date2) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date1);
    long time1 = cal.getTimeInMillis();
    cal.setTime(date2);
    long time2 = cal.getTimeInMillis();
    long betweenDays = (time2 - time1) / (1000 * 3600 * 24);

    return Integer.parseInt(String.valueOf(betweenDays));
  }

  /**
   * 计算两个日期之间相差的秒数
   *
   * @param date1
   * @param date2
   * @return
   */
  public static Long dayTimesBetween(Date date1, Date date2) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date1);
    long time1 = cal.getTimeInMillis();
    cal.setTime(date2);
    long time2 = cal.getTimeInMillis();

    return (time2 - time1) / (1000);
  }


  /**
   * 计算当前日期相对于"1977-12-01"的天数
   *
   * @param date
   * @return
   */
  public static long getRelativeDays(Date date) {
    Date relativeDate = DateUtil.parseDate(DATE_PATTERN_DEFAULT, "1977-12-01");

    return DateUtil.daysBetween(relativeDate, date);
  }

  public static Date getDateBeforTwelveMonth() {
    String date = "";
    Calendar cla = Calendar.getInstance();
    cla.setTime(getCurrentDate());
    int year = cla.get(Calendar.YEAR) - 1;
    int month = cla.get(Calendar.MONTH) + 1;
    if (month > 9) {
      date = String.valueOf(year) + DATE_DIVISION + String.valueOf(month) + DATE_DIVISION + "01";
    } else {
      date =
          String.valueOf(year) + DATE_DIVISION + "0" + month + DATE_DIVISION + "01";
    }

    return parseDate(date);
  }

  public static Date getDate(Date date, int num) {
    Calendar calendar = Calendar.getInstance(); // 得到日历
    calendar.setTime(date);// 把当前时间赋给日历
    calendar.add(Calendar.DAY_OF_MONTH, num); // 设置为前/后一天

    return calendar.getTime();
  }

  public static String getDateToString(Date date, int num, String pattern) {
    Date d = getDate(date, num);
    return format(d, pattern);
  }

  public static String getDateByMonthToString(Date date, int num, String pattern) {
    Calendar calendar = Calendar.getInstance(); // 得到日历
    calendar.setTime(date);// 把当前时间赋给日历
    calendar.add(Calendar.MONTH, num); // 设置为前/后一个月
    return format(calendar.getTime(), pattern);
  }

  public static Date getDateByHour(Date date, int num) {
    Calendar calendar = Calendar.getInstance(); // 得到日历
    calendar.setTime(date);// 把当前时间赋给日历
    calendar.add(Calendar.HOUR, num); // 设置为前/后小时

    return calendar.getTime();
  }

  public static String getAMNNPMStr(Date date, Locale locale) {

    if (get24Hours(date) == 12) {
      if (StringUtils.equals(locale.getLanguage(), Locale.ENGLISH.getLanguage())) {
        return "NN";
      }
      if (StringUtils.equals(locale.getLanguage(), Locale.CHINESE.getLanguage())) {
        return "中午";
      }
    }

    return format(date, "yyyy-MM-dd HH:mm:ss a");
  }


  public static Date getDate() {
    return new Date();
  }

  /**
   * 获取某日期是几号
   *
   * @param date
   * @return
   */
  public static int getDayOfMonth(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    return calendar.get(Calendar.DAY_OF_MONTH);
  }

  /**
   * 获取某日期是星期几
   *
   * @param date
   * @return
   */
  public static int getDayOfWeek(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    return calendar.get(Calendar.DAY_OF_WEEK);
  }

  /**
   * 获取某日期是星期几
   *
   * @param date
   * @return
   */
  public static Integer getDayOfWeekCn(Date date) {
    int dayOfWeek = getDayOfWeek(date);
    switch (dayOfWeek) {
      case 1:
        return 7;
      case 2:
        return 1;
      case 3:
        return 2;
      case 4:
        return 3;
      case 5:
        return 4;
      case 6:
        return 5;
      case 7:
        return 6;
      default:
        return null;
    }
  }

  /**
   * 获取某日期是星期几
   *
   * @param date
   * @return
   */
  public static String getDayOfWeekString(Date date) {
    int dayOfWeek = getDayOfWeek(date);
    switch (dayOfWeek) {
      case 1:
        return "Sunday";
      case 2:
        return "Monday";
      case 3:
        return "Tuesday";
      case 4:
        return "Wednesday";
      case 5:
        return "Thursday";
      case 6:
        return "Friday";
      case 7:
        return "Saturday";
      default:
        return null;
    }
  }

  /**
   * 获取某日期是星期几
   *
   * @param date
   * @return
   */
  public static String getDayOfWeekShortString(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

    switch (dayOfWeek) {
      case 1:
        return "Sun";
      case 2:
        return "Mon";
      case 3:
        return "Tue";
      case 4:
        return "Wed";
      case 5:
        return "Thu";
      case 6:
        return "Fri";
      case 7:
        return "Sat";
      default:
        return null;
    }
  }

  public static Date addWeeks(Date date, int num) {

    Calendar cal = Calendar.getInstance();
    cal.setTime(date);

    cal.add(Calendar.WEEK_OF_MONTH, num);

    return cal.getTime();
  }

  public static Date addHours(Date date, int num) {

    Calendar cal = Calendar.getInstance();
    cal.setTime(date);

    cal.add(Calendar.HOUR_OF_DAY, num);

    return cal.getTime();
  }

  public static Date addMonths(Date date, int num) {

    Calendar cal = Calendar.getInstance();
    cal.setTime(date);

    cal.add(Calendar.MONTH, num);

    return cal.getTime();
  }

  public static int getMonth(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    return cal.get(Calendar.MONTH);
  }

  public static int getMins(Date date) {
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    return c.get(Calendar.MINUTE);
  }

  /**
   * @param date
   * @return k->yyyy-MM-dd,v->MMM
   */
  public static Map<String, String> getMonthOfYearMap(Date date) {
    Map<String, String> dateMap = new LinkedHashMap<>();

    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.set(Calendar.MONTH, 0);

    Date d = calendar.getTime(); // first month of year

    for (int i = 0; i < 12; i++) {
      Date resultDate = DateUtil.addMonths(d, i);

      String key = DateUtil.format(resultDate, "yyyy-MM");

      DateFormat df = new SimpleDateFormat("MMM", Locale.ENGLISH);
      String value = df.format(resultDate);

      dateMap.put(key, value);
    }

    return dateMap;
  }

  public static String parseDate2(Date date, String pattern) {
    DateFormat df = new SimpleDateFormat(pattern);
    return df.format(date);
  }

  public static Date getDateStart(Date date) {
    String dateStr = parseDate2(date, DATE_PATTERN_DEFAULT);
    dateStr = dateStr + " 00:00:00";
    return parseDateTime(dateStr);
  }

  public static Date getDateEnd(Date date) {
    String dateStr = parseDate2(date, DATE_PATTERN_DEFAULT);
    dateStr = dateStr + " 23:59:59";
    return parseDateTime(dateStr);
  }

  public static int get24Hours(Date date) {

    Calendar c = Calendar.getInstance();
    c.setTime(date);

    return c.get(Calendar.HOUR_OF_DAY);
  }

  public static int get12Hours(Date date) {

    Calendar c = Calendar.getInstance();
    c.setTime(date);

    return c.get(Calendar.HOUR);
  }

  public static Date getAfterDate(Date date, int days) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + days);
    return calendar.getTime();
  }

  public static Date getBeforeDate(Date date, int days) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - days);
    return calendar.getTime();
  }

  public static Date getAfterHour(Date date, int hours) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + hours);
    return calendar.getTime();
  }

  public static Date getAfterMin(Date date, int min) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + min);
    return calendar.getTime();
  }

  /**
   * 获取现在到今晚12点前的秒数
   *
   * @return
   */
  public static Long getDayTime() {

    return DateUtil
        .dayTimesBetween(DateUtil.getCurrentDate(), DateUtil.getDateEnd(DateUtil.getCurrentDate()));
  }


  /**
   * 获取本周日期时间 , day=1 第一天, day=7  第7天
   *
   * @param day
   * @return
   */
  public static String getThisWeekDay(int day) {
    Calendar c = Calendar.getInstance();
    int dayOfWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayOfWeek == 0) {
			dayOfWeek = 7;
		}
    c.add(Calendar.DATE, -dayOfWeek + day);

    return DateUtil.format(c.getTime());
  }

  /**
   * 返回日期是本周的第几天  1-Monday -- 7 Sunday
   *
   * @param date
   * @return
   */
  public static int getWhickDay(Date date) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    int whichDay1 = cal.get(Calendar.DAY_OF_WEEK) - 1;
    if (0 == whichDay1) {
      whichDay1 = 7;
    }
    return whichDay1;
  }


  /**
   * 返回年数
   *
   * @param date
   * @return
   */
  public static int getYear(Date date) {

    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    return calendar.get(Calendar.YEAR);
  }

  /**
   * 返回年数
   *
   * @param long
   * @return
   */
  public static int getYear(Long date) {

    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(date);
    return calendar.get(Calendar.YEAR);
  }

  /**
   * 返回距离指定时间秒/分/时/天之前的时间
   *
   * @param args
   * @throws ParseException
   */
  public static Date getDateBeforeTime(Date date, String datePart, Integer time) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    if (DATE_SECOND.equalsIgnoreCase(datePart)) {
      calendar.add(Calendar.SECOND, time * (-1));

    } else if (DATE_MINUTES.equalsIgnoreCase(datePart)) {
      calendar.add(Calendar.MINUTE, time * (-1));

    } else if (DATE_HOUR.equalsIgnoreCase(datePart)) {
      calendar.add(Calendar.HOUR_OF_DAY, time * (-1));

    } else if (DATE_DAY.equalsIgnoreCase(datePart)) {
      calendar.add(Calendar.DATE, time * (-1));
    } else if (DATE_MONTH.equalsIgnoreCase(datePart)) {
      calendar.add(Calendar.MONTH, time * (-1));
    } else if (DATE_YEAR.equalsIgnoreCase(datePart)) {
      calendar.add(Calendar.YEAR, time * (-1));
    }
    return calendar.getTime();
  }

  /**
   * 返回距离指定时间秒/分/时/天之后的时间
   *
   * @param args
   * @throws ParseException
   */
  public static Date getDateAfterTime(Date date, String datePart, Integer time) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    if (DATE_SECOND.equalsIgnoreCase(datePart)) {
      calendar.add(Calendar.SECOND, time);

    } else if (DATE_MINUTES.equalsIgnoreCase(datePart)) {
      calendar.add(Calendar.MINUTE, time);

    } else if (DATE_HOUR.equalsIgnoreCase(datePart)) {
      calendar.add(Calendar.HOUR_OF_DAY, time);

    } else if (DATE_DAY.equalsIgnoreCase(datePart)) {
      calendar.add(Calendar.DATE, time);
    } else if (DATE_MONTH.equalsIgnoreCase(datePart)) {
      calendar.add(Calendar.MONTH, time);
    } else if (DATE_YEAR.equalsIgnoreCase(datePart)) {
      calendar.add(Calendar.YEAR, time);
    }
    return calendar.getTime();
  }

  /**
   * 返回指定语言下特定格式的时间字符串
   *
   * @param args
   * @throws ParseException
   */
  public static String formatByLang(Date date, String pattern, String lang) {
		String output = "";
		SimpleDateFormat sf = new SimpleDateFormat(pattern, Locale.SIMPLIFIED_CHINESE);
		if (TIME_LANGUGE_EN.equalsIgnoreCase(lang)) {
			sf = new SimpleDateFormat(pattern, Locale.ENGLISH);
			output = sf.format(date);
		} else if (TIME_LANGUGE_HK.equalsIgnoreCase(lang)) {
			sf = new SimpleDateFormat(pattern, Locale.TRADITIONAL_CHINESE);
			output = sf.format(date);
		} else if (TIME_LANGUGE_ID.equalsIgnoreCase(lang)) {
			sf = new SimpleDateFormat(pattern, new Locale("id", "ID"));
			output = sf.format(date);
		} else if (TIME_LANGUGE_UR.equalsIgnoreCase(lang)) {
			sf = new SimpleDateFormat(pattern, new Locale("ur", "PK"));
			output = sf.format(date);
		} else {
			output = sf.format(date);
		}
    return output;
  }

}
