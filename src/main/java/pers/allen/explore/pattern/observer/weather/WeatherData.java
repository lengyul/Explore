package pers.allen.explore.pattern.observer.weather;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现主题为可观察者
 * @author lengyul
 *
 */
public class WeatherData implements Subject {
	
	private float temperature; //温度
	
	private float humidity;  //湿度
	
	private float pressure; //压力
	
	private List<Observer> observers = null;
	
	public WeatherData(){
		observers = new ArrayList<>();
	}
	
	@Override
	public void registerObserver(Observer o) {
		observers.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		observers.remove(o);
	}

	@Override
	public void notifyObserver() {
		for (Observer item : observers) {
			item.update(temperature, humidity, pressure);
		}
	}
	
	/**
	 * 测量值更新
	 * @param temperature
	 * @param humidity
	 * @param pressure
	 */
	public void measurementsChanged(float temperature,float humidity,float pressure){
		this.temperature = temperature;
		this.humidity = humidity;
		this.pressure = pressure;
		notifyObserver(); //通知观察者
	}

	public float getTemperature() {
		return temperature;
	}

	public float getHumidity() {
		return humidity;
	}

	public float getPressure() {
		return pressure;
	}
	
	

}
