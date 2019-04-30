package pers.allen.explore.pattern.skeletal;


import java.io.IOException;

public class ChannelTest {
	
	public static void main(String[] args) throws IOException {
		
		Channel channel = new FileChannel("C:\\Users\\11749\\Desktop\\000.txt");
	//	System.out.println(channel.getStreamSource());
	//	System.out.println(channel.getCurrentImplClass());
		
		byte[] bytes = new byte[2];
		System.out.println(channel.read(bytes));
		System.out.println(new String(bytes));
		channel.close();
		/*Channel channel2 = new SocketChannel(null);
		channel2.close();*/
	}
}
