package com.explore.test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.junit.Test;


/**
 * 类加载器：Java类可以被动态加载到Java虚拟机中并执行。
 * @author lengyul
 * @date 2019年1月18日 下午2:08:45
 * 	Java源程序（.java文件）在经过Java编译之后被转换成Java字节码（.class文件）。类加载器
 * 负责读取Java字节码，并转换成java.lang.Class类的一个实例。
 * ClassLoader中与加载类相关的方法
 * 	getParent();返回该类加载器的父类加载器。
 * 	loadClass(String name);加载名称为 name的类，返回的结果是 java.lang.Class类的实例。
 *  findClass(String name);查找名称为 name的类，返回的结果是 java.lang.Class类的实例。
 *  findLoadedClass(String name);查找名称为 name的已经被加载过的类，返回的结果是 java.lang.Class类的实例。
 *  defineClass(String name, byte[] b, int off, int len);
 *  把字节数组 b中的内容转换成 Java 类，返回的结果是 java.lang.Class类的实例。这个方法被声明为 final的。
 *  resolveClass(Class<?> c);链接指定的 Java 类。 
 */
public class ClassLoaderTest {
	
	@Test
	public void testTree() {
		// 演示类加载器的树状组织结构
		ClassLoader loader = ClassLoaderTest.class.getClassLoader();
		while (loader != null) {
			System.out.println(loader.toString());
			loader = loader.getParent();
		}
		// sun.misc.Launcher$AppClassLoader@73d16e93 系统类加载器
		// sun.misc.Launcher$ExtClassLoader@15db9742 扩展类加载器
	}
	
	@Test
	public void testClassIdentity() {
	//	String classDataRootPath = "C:\\Users\\Administrator\\Desktop\\ParkingIcbcPaySon\\WEB-INF\\classes\\";
		//	String className = "com.parkingicbcpay.dto.BaseReq";

		String classDataRootPath = "F:\\eclipse-mars\\eclipseWork\\Explore\\target\\classes\\"; 
		String className = "com.explore.test.Sample";
		FileSystemClassLoader fscl1 = new FileSystemClassLoader(classDataRootPath); 
		FileSystemClassLoader fscl2 = new FileSystemClassLoader(classDataRootPath); 
		
		try {
			Class<?> class1 = fscl1.loadClass(className);
			Object obj1 = class1.newInstance();
			Class<?> class2 = fscl2.loadClass(className);
			
			System.out.println(class1.getClassLoader());
			System.out.println(class2.getClassLoader());
			
			Object obj2 = class2.newInstance();			
			Method setSampleMethod = class1.getMethod("setSample", java.lang.Object.class);
			setSampleMethod.invoke(obj1,obj2);
			System.out.println(obj1 + " " +obj2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testFileSystemClassLoader() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		//文件加载器
		FileSystemClassLoader fscl = new FileSystemClassLoader("F:\\eclipse-mars\\eclipseWork\\Explore\\target\\classes\\");
		Class<?> clazz = fscl.loadClass("com.explore.utils.RandomUtils");
		System.out.println(clazz);
		System.out.println("instance "+clazz.newInstance());
		System.out.println("classloader "+clazz.getClassLoader());
		System.out.println("methods "+Arrays.asList(clazz.getMethods()).toString());
	}
	
	
	private class FileSystemClassLoader extends ClassLoader {
		
		private String rootDir;
		
		public FileSystemClassLoader(String rootDir){
			this.rootDir = rootDir;
		}
		
		protected Class<?> findClass(String name) throws ClassNotFoundException {
			byte[] classData = getClassData(name);
			if (classData == null) {
				throw new ClassNotFoundException();
			} else {
				return defineClass(name, classData, 0, classData.length);
			}
		}
		
		private byte[] getClassData(String className) {
			String path = classNameToPath(className);
			try {
				InputStream ins = new FileInputStream(path);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				int bufferSize = 4096;
				byte[] buffer = new byte[bufferSize];
				int bytesNumRead = 0;
				while ((bytesNumRead = ins.read(buffer)) != -1) {
					baos.write(buffer,0,bytesNumRead);
				}
				return baos.toByteArray();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		private String classNameToPath(String className) {
			return rootDir + File.separatorChar 
		               + className.replace('.', File.separatorChar) + ".class"; 
		}
		
	}
}
class Sample {
	Sample instance;	
	public void setSample(Object instance) {
		this.instance = (Sample) instance;
	}
}
	

