package com.explore.pattern.observer.weather;

public interface Observer {
	
	void update(float temperature,float humidity,float pressure);
}
