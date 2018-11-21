package com.expolre.pattern.observer.weather;

public class CurrentConditionsDisplay implements Observer,DisplayElement{
	
	private float temperature;
	
	private float humidity;
	
	private float pressure;
	
	private WeatherData weatherData;
	
	public WeatherData getWeatherData() {
		return weatherData;
	}
	
    public CurrentConditionsDisplay(WeatherData weatherData) {
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
		System.out.println(this.getClass().getSimpleName()+"：当前温度值："+temperature+"℃，当前湿度值："+humidity+"%，当前压力值："+pressure);
	}
	

}
