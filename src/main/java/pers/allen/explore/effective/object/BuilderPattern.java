package pers.allen.explore.effective.object;

/**
 * 建造者（Builder）模式
 * @author lengyul
 * @date 2019年3月30日 下午1:25:47
 */
public class BuilderPattern {
	
	/*
	 * builder的设值方法返回builder本身，把调用连接起来，等到一个流式的API
	 * BuilderPattern object = new BuilderPattern.Builder(1).age(20).name("Allen").build();
	 * Builder模式模拟了具名的可选参数，易于阅读
	 * 与构造器相比，它可以有多个可变参数（多次调用同一个设值方法），因为builder是利用单独的方法来设置每一个参数，但是为了创建对象，必须先创建它的构建器
	 * 如果类的构造器或者静态工厂中具有多个参数，设计这种类时，Builder模式是一种不错的选择
	 */
	private final int id;
	private final int age;
	private final int heigh;
	private final int weight;
	private final String name;
	
	private BuilderPattern(Builder builder) {
		id = builder.id;
		age = builder.age;
		heigh = builder.heigh;
		weight = builder.weight;
		name = builder.name;
	}
	
	@Override
	public String toString() {
		return "BuilderPattern [id=" + id + ", age=" + age + ", heigh=" + heigh + ", weight=" + weight + ", name="
				+ name + "]";
	}

	public static class Builder {
		// required parameters
		private final int id;
		// optional parameters
		private int age;
		private int heigh;
		private int weight;
		private String name;
		
		public Builder(int id) {
			this.id = id;
		}
		
		public Builder age(int val) {
			age = val;
			return this;
		}
		public Builder heigh(int val) {
			heigh = val;
			return this;
		}
		public Builder weight(int val) {
			weight = val;
			return this;
		}
		public Builder name(String val) {
			name = val;
			return this;
		}
		
		public BuilderPattern build() {
			return new BuilderPattern(this);
		}
	}
	
	
}
