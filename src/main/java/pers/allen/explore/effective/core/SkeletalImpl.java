package pers.allen.explore.effective.core;

import java.io.Closeable;
import java.io.IOException;

/**
 * 骨架实现（可以把接口和抽象类的优点结合起来）
 * @author lengyul
 * @date 2019年4月25日 下午7:08:58
 */
public class SkeletalImpl {

	/*
	 * 概述：
	 * 接口负责定义类型，骨架实现类负责实现基本类型接口之外，剩下的非基本类型接口方法。
	 * Collections Framework为每个重要的集合接口都实现了一个骨架实现，如：
	 * AbstractCollection、AbstractList、AbstractSet、AbstractMap
	 * Skeletal...
	 * 
	 * 定义：
	 * 骨架实现类名称 = Abstract + Interface（实现的接口名称） 
	 * 骨架实现类的设计：首先确定哪些方法是最为基本的（声明到接口中），基本方法成为骨架实现类的抽象方法，
	 * 其它的方法可以根据它来实现，如果基本方法和默认方法覆盖了接口，那么就不需要骨架实现类
	 * 
	 * 原因：
	 * 骨架实现模式设计是因为Java接口在1.8之前不允许提供方法的实现，现在完全可以把骨架实现类
	 * 实现的方法放到接口中使用default方法来实现
	 * 
	 */
	private interface Channel extends Closeable {
		// 基本方法
		String id();
	
		int read(byte b[]);

		int write(byte[] bytes);

		
		// 其他方法，需要骨架实现类去实现
		Channel setChannel();

		String toString();

		// ...不允许为 Object方法提供缺省实现

	}

	private abstract class AbstractChannel implements Channel {

		public abstract String id();

		public abstract int read(byte b[]);

		public abstract int write(byte[] bytes);

		@Override
		public Channel setChannel() {
			throw new UnsupportedOperationException();
		}

		@Override
		public String toString() {

			return id();
		}

	}

	/*
	 * 该子类是否还要明确的实现Channel接口 ？
	 * 但是作为骨架实现类已经实现了Channel接口，子类自然也就实现了
	 * 论坛讨论该主题：https://www.iteye.com/topic/385733
	 */
	@SuppressWarnings("unused")
	private class ChannelImpl extends AbstractChannel implements Channel {

		@Override
		public void close() throws IOException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public String id() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int read(byte[] b) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int write(byte[] bytes) {
			// TODO Auto-generated method stub
			return 0;
		}

	}
	
	
}
