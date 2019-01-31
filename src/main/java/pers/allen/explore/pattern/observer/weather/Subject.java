package pers.allen.explore.pattern.observer.weather;

/**
 * 主题
 * @author lengyul
 *
 */
public interface Subject {
	
	void registerObserver(Observer o);
	
	void removeObserver(Observer o);
	
	void notifyObserver();
}
