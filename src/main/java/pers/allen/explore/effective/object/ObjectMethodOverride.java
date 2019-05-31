package pers.allen.explore.effective.object;

/**
 * 覆盖Object中的非final方法（通用约定）
 * @author lengyul
 * @date 2019年4月17日 下午5:41:26
 */
public final class ObjectMethodOverride implements Cloneable {
	
	private final int x;
	private final int y;
	
	private int[] elements = new int[1];
	
	int hash = 0;
	
	public ObjectMethodOverride(int x,int y) {
		this.x = x;
		this.y = y;
	}
	
	/*
	 * 如果类有自己特有的 逻辑相等[值类（Integer，String）]，只希望它们在逻辑上是否相等，而不是指向同一个对象。
	 * 例如：Abstract[List、Map、Set]等
	 * 覆盖equals方法通用约定：
	 * 自反性：x.equals(x) == true  // x != null		
	 * 对称性：x.equals(y) == y.equals(x) == true
	 * 传递性：x.equals(y) == y.equals(z) == x.equals(z)  == true
	 * 一致性：x.equals(y)		// 如果equals比较的对象所用信息没有发生改变，多次调用应该返回同样的结果
	 * 非空性：x.equals(null) == false 
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this) 
			return true;
		if (obj instanceof ObjectMethodOverride) {
			ObjectMethodOverride omo = (ObjectMethodOverride)obj;
			return omo.x == x && omo.y == y;
		}
		return false;
	}
	
	
	
	/*
	 * 覆盖equals方法必须覆盖hashcode方法（如果不覆盖可能导致该类无法结合所有基于散列的集合正常运作
	 * ，两个相等的实例具有不相等的散列码）
	 * 同一个对象的多次调用（对象所用信息没有被修改），hashCode方法必须返回同一个值
	 * equals相等的两个对象必须具有相等的散列码
	 * equals不相等的两个对象，不一定要求hashCode方法产生不同的结果（理想情况：为不相等的对象产生不同的散列码）
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int result = hash; 
		if (result == 0) {
		//	result = Objects.hash(x,y);	 // 使用Objects.hash方法性能可能较低（可变参数创建数组以及基本类型装箱拆箱）
			result = 1;
			result = 31 * result + Integer.hashCode(x);
			result = 31 * result + Integer.hashCode(y);
			hash = result; // 缓存散列码，参考String.class
		}
		return result;
	}
	

	
	/*
	 * 覆盖toString方法返回对象中包含的所有值得关注的信息，使对象变得简洁更易于调试及阅读
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ObjectMethodOverride [x=" + x + ", y=" + y + ", element[0]=" +elements[0]+"]";
	}
	
	/* 
	 * 覆盖clone方法需要该子类实现 Cloneable接口   详见：（marker interface）
	 * 不可变类不应该覆盖clone方法（即引用可变对象使用final域）
	 * Object中的clone方法为浅拷贝，当克隆新对象对其"引用类型"的属性进行修改后，原对象的引用类型属性也会发生改变，反之亦然
	 * (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	protected ObjectMethodOverride clone() throws CloneNotSupportedException {
		ObjectMethodOverride o = (ObjectMethodOverride) super.clone();
		return o;
	}
	
	/*@Override
	protected ObjectMethodOverride clone() throws CloneNotSupportedException {
		ObjectMethodOverride o = (ObjectMethodOverride) super.clone();
		o.elements = this.elements.clone(); // 如果elements为final则不能进行clone
		return o;
	}*/
	
	
	public static void main(String[] args) throws CloneNotSupportedException {
		// equals()
		ObjectMethodOverride omo1 = new ObjectMethodOverride(1, 2);
		ObjectMethodOverride omo2 = new ObjectMethodOverride(1, 2);
		ObjectMethodOverride omo3 = new ObjectMethodOverride(1, 2);
		System.out.println(omo1.equals(omo1));
		System.out.print(omo1.equals(omo2)+ "\t");System.out.println(omo2.equals(omo1));
		System.out.print(omo1.equals(omo3)+ "\t");System.out.println(omo2.equals(omo3));
		for (int i = 0; i < 10; i++) {
			System.out.print(omo1.equals(omo2)+ "\t");
		}
		System.out.println();
		System.out.println(omo1.equals(null));
		
		// hashCode()  如果覆盖equals而不覆盖hashCode()时，返回 false
		System.out.println(omo1.hashCode() == omo2.hashCode());
		
		// toString()
		System.out.println(omo1.toString());
		
		// clone()
		ObjectMethodOverride cloneObj = omo1.clone();
		System.out.println("clone:"+ cloneObj);
		
		cloneObj.elements[0] = 1;
		System.out.println("clone:" + cloneObj.toString());
		System.out.println("orig:" +omo1.toString());
	}
	

}
