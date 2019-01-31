package pers.allen.explore.pattern.factory.car;

import java.util.Arrays;
import java.util.List;


/**
 * 简单工厂：使用该工厂，通过传递类型信息来获取实体类的对象。
 * 缺点：因为是静态的，不能够通过继承来改变创建方法的行为。
 * @author lengyul
 *
 */
public class FactoryPatternDemo {
	
	public static void main(String[] args) {
		/*SimpleCarFactory carFactory =new SimpleCarFactory();
		Car carAudi = carFactory.getCar("Audi");
		Car carBenz = carFactory.getCar("Benz");
		Car carBMW  = carFactory.getCar("BMW");*/

		CarStore carStore = new CarStore(new SimpleCarFactory());
		carStore.create("Audi");
		carStore.create("Benz");
		carStore.create("BMW");
		/*List<Car> cars =Arrays.asList(carAudi,carBenz,carBMW);
		cars.stream().forEach(System.out::println);*/
	}
}
