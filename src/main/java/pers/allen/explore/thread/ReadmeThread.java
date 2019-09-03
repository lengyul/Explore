package pers.allen.explore.thread;

/**
 * Thread类中的属性方法描述
 * @author lengyul
 * @date 2019年5月28日 上午10:36:43
 */
public class ReadmeThread {
	
	/*
	 * @see Thread.class
	 * 
	 * 	   操作系统调度的最小单元，一个进程可以包含多个线程，作为程序中任务的执行者，有自己的 栈 、寄存器、本地存储等，但是会和
	 * 进程内其他线程共享文件描述符、虚拟地址空间
	 * 
	 * name 线程名称：如果在创建线程时未指定名称，则会为其生成一个新名称（Thread-x）
	 * priority 线程优先级：具有较高优先级的线程优先于优先级较低的线程执行（最小为1，默认为5，最大为10）
	 *					 Thread.MIN_PRIORITY Thread.NORM_PRIORITY Thread.MAX_PRIORITY
	 * threadQ   
	 * eetop
	 * single_step 
	 * daemon @see DaemonTest 守护线程：当所有的非守护线程结束时，守护线程也会停止运行
	 * stillborn JVM状态
	 * target @see Runnable 执行的线程对象
	 * group  @see ThreadGroup 线程组：指定当前线程的线程组，可以用来管理一组线程，没有实际意义
	 * contextClassLoader @see ClassLoader 该线程的上下文类加载器
	 * inheritedAccessControlContext 该线程继承的 @see AccessControlContext
	 * threadInitNumber -> nextThreadNum() 生成线程名称时的自动编号
	 * threadLocals @see ThreadLocal.ThreadLocalMap ThreadLocal的值，由ThreadLocal类维护
	 * inheritableThreadLocals InheritableThreadLocal的值，由InheritableThreadLocal类维护，继承 ThreadLocal类
	 * stackSize
	 * nativeParkEventPointer
	 * tid -> nextThreadID() 线程的唯一标识
	 * threadStatus 线程状态：（新建状态、运行状态、阻塞状态、等待状态、等待超时状态、终止状态），由native方法更新该值
	 * parkBlocker
	 * blocker
	 */
	
	/*
	 * Thread.State
	 * NEW         Thread t = new Thread(() -> { // doSomething });
	 * RUNNABLE	   t.start();  
	 * BLOCKED	   synchronized  |  Blocking I/O ...
	 * WAITING	   Object.wait() | Thread.join() ...
	 * TIMED_WAITING Object.wait(long timeout) 超时
	 * TERMINATED  线程运行结束或异常退出
	 */
}
