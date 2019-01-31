package pers.allen.explore.utils;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Java8日期工具类
 * 
 * @author lengyul
 * 
 */
public class DateTimeUtils {

	public static final DateTimeFormatter DTF_DATE = DateTimeFormatter.ISO_DATE;

	public static final DateTimeFormatter DTF_DATE_TIME = DateTimeFormatter.ISO_DATE_TIME;

	public static final DateTimeFormatter DTF_DATE_YMD = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public static final DateTimeFormatter DTF_DATE_YMDHMS = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	/**
	 * 返回当前时间(ISO-8601格式)
	 * 
	 * @return
	 */
	public static String getNowTime() {
		LocalDateTime ldt = LocalDateTime.now();
		return ldt.toString();
	}

	/**
	 * 返回当前时间(UTC格式)-相差8小时
	 * 
	 * @return
	 */
	public static String getUTCNowTime() {
		Instant instant = Instant.now(); // 相差8小时
		return instant.toString();
	}

	/**
	 * 返回当前时间指定格式(参数类型-String)
	 * 
	 * @param pattern
	 * @return
	 */
	public static String getNowTime(String pattern) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
		LocalDateTime ldt = LocalDateTime.now();
		return ldt.format(dtf);
	}

	/**
	 * 返回当前时间指定格式(参数类型-DateTimeFormatter)
	 * 
	 * @param dtf
	 * @return
	 */
	public static String getNowTime(DateTimeFormatter dtf) {
		LocalDateTime ldt = LocalDateTime.now();
		return ldt.format(dtf);
	}

	/**
	 * 返回指定时区的时间(参数类型-String)
	 * 
	 * @param zone
	 * @return
	 */
	public static String getNowTimeByZone(String zone) {
		LocalDateTime ldt = LocalDateTime.now(ZoneId.of(zone));
		// ZonedDateTime zdt = ldt.atZone(ZoneId.of(zone));//带时区
		return ldt.toString();
	}

	/**
	 * 返回LocalDateTime类型(时间,类型)
	 * 
	 * @param time
	 * @param dtf
	 * @return
	 */
	public static LocalDateTime getParseLDT(String time, DateTimeFormatter dtf) {
		LocalDateTime ldt = LocalDateTime.now();
		return ldt.parse(time, dtf);
	}

	/**
	 * 返回当前时间加上X天(参数类型-int)
	 * 
	 * @param days
	 * @return
	 */
	public static String getPlusDayTime(int days) {
		LocalDateTime ldt = LocalDateTime.now();
		return ldt.plusDays(days).toString();
	}

	/**
	 * 返回当前时间减上X天(参数类型-int)
	 * 
	 * @param days
	 * @return
	 */
	public static String getMinusDayTime(int days) {
		LocalDateTime ldt = LocalDateTime.now();
		return ldt.minusDays(days).toString();
	}

	/**
	 * 计算两个时间时差
	 * 
	 * @param lt1
	 * @param lt2
	 * @return
	 */
	public static Duration getBetweenTime(LocalTime lt1, LocalTime lt2) {
		Duration duration = Duration.between(lt1, lt2);
		return duration;
	}

	/**
	 * 计算两个日期的间隔
	 * 
	 * @param ld1
	 * @param ld2
	 * @return
	 */
	public static Period getBetweenDate(LocalDate ld1, LocalDate ld2) {
		Period period = Period.between(ld1, ld2);
		return period;
	}

}
