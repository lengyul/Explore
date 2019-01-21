package com.explore.code.codec;

import java.io.Serializable;
import java.nio.ByteBuffer;

public class UserInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UserInfo(String username){
		this.username = username;
	}
	
	public UserInfo(String username,int userID){
		this.username = username;
		this.userID = userID;
	}

	private String username;
	
	private int userID;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	@Override
	public String toString() {
		return "UserInfo [username=" + username + ", userID=" + userID + "]";
	}
		
	public byte[] codeC(){
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		byte [] value = this.username.getBytes();
		buffer.putInt(value.length); //字节长度
		buffer.put(value);
		buffer.putInt(this.userID); //userID为int类型
		buffer.flip(); //切换到读模式,postion为0
		byte [] result = new byte[buffer.remaining()]; //获取buffer可读字节长度
		buffer.get(result); //读取字节到result
		return result;
	}
	
}
