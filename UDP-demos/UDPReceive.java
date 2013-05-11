package com.baron.socket.demo;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
/*
    1）定义UDP socket服务。通常会监听一个端口(DatagramSocket(int port))。
            方便于明确哪些数据过来该应用程序要处理。
　　2）定义一个数据包。存储接收到的数据(DatagramPacket(byte[] data, int len))
　　3）通过socket服务的receive方法，将收到的数据存入已定义好的数据包中。
　　4）通过数据包对象的特有功能，将这些不同的数据取出。
　　5）关闭资源
    note:need to run this .java first then run UDPsend.java to send data to this app.

 * 
 * */
public class UDPReceive {

	public static void main(String[] args) throws Exception {
		//1.创建UDP socket,并把端口号10000绑定给这个应用程序
		DatagramSocket ds = new DatagramSocket(10000);
		while(true){
			//2.定义数据包，用于存储数据
			byte[] buf = new byte[1024];
			DatagramPacket dp = new DatagramPacket(buf, buf.length);
			//3.通过服务的receive方法将数据存入到数据包中
			ds.receive(dp);
			//4.通过数据包的方法，获取其中的数据。
			String ip = dp.getAddress().getHostAddress();//发送端的IP地址
			String data = new String(dp.getData(), 0, dp.getLength());
			int port = dp.getPort();//发送端的端口号
			System.out.println("received data");
			System.out.println(ip + "::" + data + "::" + port);
			//5.关闭资源
			//ds.close();
		}
	}
}
