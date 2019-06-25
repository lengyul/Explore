package pers.allen.explore.test;

public class InitialTest {

	public static void main(String[] args) {
		System.out.println(Life.LIFE);
		System.out.println("live：" + Life.LIVING);
		System.out.println("alive："+Life.LIFE.alive);
	}
	
	private static class Life {
		public static final Life LIFE = new Life();
		private Life() {}
	
		private static final Boolean LIVING = true;
		private final Boolean alive = LIVING;
		// alive 字段依赖于 LIVING 字段 ，但 LIVING 字段 在 LIFE 字段初始化之后，导致 alive 字段初始化失败
	}	
}
