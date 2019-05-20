package pers.allen.explore.pattern.observer.weather;

import java.time.LocalDateTime;

import org.junit.Test;

/**
 * 观察者模式： 在对象之间定义了一对多的依赖，当一个对象改变状态，依赖它的对象会收到通知并自动更新 
 * 用途：推送，广播 ，发布订阅 ---> 多个订阅者接收
 * @author lengyul
 *
 */
public class WeatherTest {

	@Test
	public void test() throws InterruptedException {
		WeatherData wd = new WeatherData();

		CurrentConditionsDisplay ccd = new CurrentConditionsDisplay(wd);
		ForecastDisplay fd = new ForecastDisplay(wd);
		// 可观察者推送数据到观察者
		while (true) {
			Thread.sleep(5000);
			System.out.println(LocalDateTime.now());
			wd.measurementsChanged(20, 84, 0);
		}

		// 观察者主动拉取数据 --- 问题：可观察者可以被观察者操作
		// System.out.println(ccd.getWeatherData().getHumidity());
		// ccd.display();
	}

}
