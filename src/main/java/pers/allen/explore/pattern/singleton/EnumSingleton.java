package pers.allen.explore.pattern.singleton;

/**
 * 使用枚举的单例模式
 * @author lengyul
 * 优势：防止多次实例化，避免复杂的序列化或者反射攻击的漏洞，单元素的枚举类型成为实现Singleton的最佳方法
 */
public class EnumSingleton{
    private EnumSingleton(){}
    public static EnumSingleton getInstance(){
        return Singleton.INSTANCE.getInstance();
    }
    
    private static enum Singleton{
        INSTANCE;
        private EnumSingleton singleton;
        // Jvm 保证此方法只调用一次
        private Singleton(){
            singleton = new EnumSingleton();
        }
        public EnumSingleton getInstance(){
            return singleton;
        }
    }
}
