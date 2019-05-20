package pers.allen.explore.feature.java8.date;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

import org.junit.Test;

public class DateOperationTest {

	// DateTimeFormatter 格式化时间/日期
	@Test
	public void testDateTimeFormatter() {
		// 获取时间格式
		// DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE_TIME;
		DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE;
		LocalDateTime ldt = LocalDateTime.now();

		// 将当前时间format为指定的格式
		String str = ldt.format(dtf);
		System.out.println(str);

		// 自定义日期格式
		DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String str2 = dtf2.format(ldt);
		System.out.println(str2);

		// 将字符串转换为日期类型
		LocalDateTime parseDate = ldt.parse(str2, dtf2);
		System.out.println(parseDate);
	}

	@Test
	public void testZoneDateTime() {
		// 获取所有时区
		/*
		 * Set<String> set = ZoneId.getAvailableZoneIds();
		 * set.forEach(System.out::println);
		 */
		// 返回指定时区的日期
		LocalDateTime ldt = LocalDateTime.now(ZoneId.of("America/Caracas"));
		System.out.println(ldt);

		// 返回带时区的时间日期
		LocalDateTime ldt2 = LocalDateTime.now();
		ZonedDateTime zdt = ldt2.atZone(ZoneId.of("Asia/Shanghai"));
		System.out.println(zdt);

	}

	// 获取本地时间 LocalDate + LocalTime = DateOperationTest
	@Test
	public void testNowTime() {
		// 获取当前时间
		LocalDateTime ldt = LocalDateTime.now();
		System.out.println(ldt); // yyyy-MM-ddThh:mm:ss.SSS ISO-8601

		// 指定某个时间
		LocalDateTime ldt2 = LocalDateTime.of(2018, 9, 05, 10, 37, 20);
		System.out.println(ldt2);

		// 时间plus
		LocalDateTime ldtplus = ldt.plusYears(1);
		System.out.println(ldtplus);

		// 时间minus
		LocalDateTime ldtminus = ldt.minusYears(1);
		System.out.println(ldtminus);

		// 获取年月日时分秒
		System.out.println(ldt.getYear());
		System.out.println(ldt.getMonthValue());
		System.out.println(ldt.getDayOfMonth());
		System.out.println(ldt.getHour());
		System.out.println(ldt.getMinute());
		System.out.println(ldt.getSecond());
		System.out.println(ldt.getDayOfWeek());
	}

	// Instant 时间戳
	@Test
	public void testUTC() {
		// 获取UTC时区 yyyy-MM-ddThh:mm:ss.SSSZ 协调世界时，又称世界统一时间、世界标准时间、国际协调时间。
		Instant instant = Instant.now(); // 相差8小时
		System.out.println(instant);
		System.out.println(instant.toEpochMilli()); // 获取毫秒数

		OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));
		System.out.println(offsetDateTime);

	}

	// Duration 计算两个时间时差,Period 计算两个日期之间的间隔
	@Test
	public void testDuration() throws InterruptedException {
		Instant instant1 = Instant.now();
		Thread.sleep(2000);
		Instant instant2 = Instant.now();

		Duration duration = Duration.between(instant1, instant2);
		System.out.println(duration.toMillis()); // ms
		System.out.println(duration.getSeconds());// s

		LocalTime localTime1 = LocalTime.now();
		Thread.sleep(2000);
		LocalTime localTime2 = LocalTime.now();

		Duration duration2 = Duration.between(localTime1, localTime2);
		System.out.println(duration2.toMillis()); // ms
		System.out.println(duration2.getSeconds());// s

		LocalDate localDate1 = LocalDate.now();
		LocalDate localDate2 = LocalDate.of(2018, 9, 4);
		Period period = Period.between(localDate1, localDate2);
		System.out.println(period.getDays());
	}

	// TemporalAdjuster 时间矫正器
	@Test
	public void testTemporalAdjuster() {
		LocalDateTime ldt = LocalDateTime.now();
		System.out.println(ldt);

		LocalDateTime ldt2 = ldt.withDayOfMonth(10); // 指定到 某天
		System.out.println(ldt2);

		// 指定到某个时间
		// LocalDateTime ldtLastDay =
		// ldt.with(TemporalAdjusters.lastDayOfYear());
		// LocalDateTime ldtFirstDay =
		// ldt.with(TemporalAdjusters.firstDayOfMonth());
		// System.out.println(ldtLastDay);
		// 自定义到某个时间,返回这个月月底日期
		LocalDateTime tday = ldt.with((l) -> {
			LocalDateTime ldtNow = (LocalDateTime) l;
			// DayOfWeek day = ldtFormat.getDayOfWeek();//获取周几
			LocalDateTime ldtLastMonth = ldtNow.with(TemporalAdjusters.lastDayOfMonth());
			int lackday = ldtLastMonth.getDayOfMonth() - ldtNow.getDayOfMonth();
			return ldtNow.plusDays(lackday);
		});
		System.out.println(tday);

	}

}
