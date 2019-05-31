package pers.allen.explore.effective.object;

/**
 * JavaBeans模式
 * @author lengyul
 * @date 2019年3月30日 下午1:07:14
 */
public class JavaBeans {

	/*
	 * 先调用无参构造方法来创建对象，然后再调用setter方法来设置每个必要的参数
	 * JavaBeans javaBeans = new JavaBeans();
	 * javaBeans.setId();
	 * javaBeans.setAge();
	 * javaBeans.setHeigh();...
	 * 
	 * JavaBeans模式自身有着很严重的缺点，因为构造过程被分到了几个调用中，在构造过插中JavaBean可能处于不一致的状态
	 * 类不能做成不可变状态
	 */
	private int id;
	private int age;
	private int heigh;
	private int weight;
	private String name;
	
	public JavaBeans() {}

	public void setId(int id) {
		this.id = id;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setHeigh(int heigh) {
		this.heigh = heigh;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
}
