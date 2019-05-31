package pers.allen.explore.effective.object;


/**
 * 创建对象（用静态工厂方法代替构造器，注：与设计模式中的工厂方法不同）
 * @author lengyul
 * @date 2019年3月26日 下午8:48:01
 * 
 */
public class ObjectInstance {
	
	private int id;
	
    private ObjectInstance() {}
    private ObjectInstance(int id) {
		this.id = id;
	}
    
    
    public int getId() {
		return id;
	}

	//缓存对象
    public static final ObjectInstance instance = new ObjectInstance();
  	public static final ObjectInstance instrance0 = new ObjectInstance(0);
    public static final ObjectInstance instrance1 = new ObjectInstance(1);
	
	/*
	 * 1.有方法名称，返回值，而构造器只能是类名
	 */
	public static ObjectInstance getInstance() {
		return new ObjectInstance();
	}
	
	/*
	 * 2.不必在每次调用时都创建一个新对象
	 * 适用于不可变类，预先构建好的实例缓存起来，进行重复利用，避免创建不必要的对象
	 * 参见：Boolean.valueOf(boolean)方法
	 */
	public static ObjectInstance getCacheInstance() {
		return instance;
	}
	
	/*
	 * 3.可以返回原返回类型的子类类型的对象
	 */
	private static class SubObjectInstance extends ObjectInstance {}
	public static ObjectInstance getSubclassInstance() {
		return new SubObjectInstance();
	}
	
	/*
	 * 4.所返回的对象可以随着每次调用而发生变化，取决于静态工厂方法的参数值设定
	 */
	public static ObjectInstance getInstanceByNumber(int number) {
		ObjectInstance instancex = null;
		switch (number) {
		case 0:
			instancex = instrance0;
			break;
		case 1:
			instancex = instrance1;
			break;
		default:
			instancex = instance;
			break;
		}
		return instancex;
	}
	
	/*
	 * 5.方法返回对象所属的类，在编写包含静态工厂方法的类时可以不存在
	 * 详见：ServiceLoader
	 */
	 public static <T> Object getObject(T t) throws InstantiationException, IllegalAccessException {
		 if (t instanceof ObjectInstance) {
			return instance;
		 } else {
			 return t.getClass().newInstance();
		 }
	 }
	 
	 /*
	  * 静态方法主要缺点在于：类如果不包含公有或者受保护的构造器，就不能被子类实例化。
	  * 其他缺点：很难发现这些方法。
	  * 静态工厂方法的一些惯用名称：	
	  * from--类型转换方法，它只有单个参数，返回该类型的一个相对应的实例
	  * 	Date d = Date.from(instant);
	  * of--聚合方法，带有多个参数，返回该类型的一个实例，把它们合并起来
	  * 	EnumSet.of(e,e,e...);	
	  * 	Stream.of(T t);
	  * valueOf
	  * 	BigInteger prime = BigInteger.valueOf(i);
	  * 	Integer prime = Integer.valueOf(i);
	  * instance或者getInstance--返回实例是通过方法来描述，一般为同一个实例
	  * 	ObjectInstance instance = ObjectInstance.getInstance();
	  * create或者newInstance--与	instance或者getInstance一样，但是能够确保每次调用都返回一个新的实例
	  * 	newInstance(componentType, length)
	  * 	instance.getClass().newInstance();
	  * getType--像getInstance一样，但是在不同的类中的时候使用
	  * 	FileStore fs = Files.getFileStore(path);
	  * newType--与newInstance一样，但是在不同的类中的时候使用
	  * 	InputStream in = newInputStream(path, options);
	  * type--getType和newType的简版
	  * 	List<T> list = Collections.list(e);
	  */
}
