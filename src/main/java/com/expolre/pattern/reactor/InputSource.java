package com.expolre.pattern.reactor;

/**
 * 输入对象
 * @author lengyul
 * @date 2018年12月11日 下午4:18:45
 */
public class InputSource {
	
	private String data;
	
	public InputSource(String data){
		this.data = data;
	}

	@Override
	public String toString() {
		return "InputSource [data=" + data + "]";
	}
	
	
}
