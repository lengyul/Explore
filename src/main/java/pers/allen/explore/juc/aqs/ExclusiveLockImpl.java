package pers.allen.explore.juc.aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 使用AQS模拟一个独占锁
 * @author lengyul
 * @date 2019年6月10日 下午3:06:06
 */
public class ExclusiveLockImpl {
	
	private Sync sync = new Sync();

	public void lock() {
		sync.acquire(1); // -> tryAcquire
	}

	public void unlock() {
		sync.release(1); // -> tryRelease
	}

	private static final class Sync extends AbstractQueuedSynchronizer {

		@Override
		protected boolean tryAcquire(int arg) {
			return compareAndSetState(0, 1); // state [0 -> 1]
		}

		@Override
		protected boolean tryRelease(int arg) {
			setState(0); // state [1 -> 0]
			return true;
		}

		@Override
		protected boolean isHeldExclusively() {
			return getState() == 1;
		}

	}

}
