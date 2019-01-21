package com.explore.pattern.observer.weather;

public class ForecastDisplay implements Observer,DisplayElement{

	private float temperature; //温度
	
	private float humidity;  //湿度
	
	private float pressure; //压力
	
	private WeatherData weatherData;
	
	public ForecastDisplay(WeatherData weatherData) {
		this.weatherData = weatherData;
		this.weatherData.registerObserver(this);
	}
	
	@Override
	public void update(float temperature, float humidity, float pressure) {
		this.temperature = temperature;
		this.humidity = humidity;
		this.pressure = pressure;
		display();
	}

	@Override
	public void display() {
		System.out.println(this.getClass().getSimpleName()+"：当前最大温度值："+temperature+"℃，当前最大湿度值："+humidity+"%");
	}

}
