package com.baron.socket.demo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/*
 * 1.建立UDP socket服务(通过DatagramSocket对象)
 * 2.提供数据，并交数据封装到数据包中(通过DatagramPacket对象)
 * 3.通过socket服务的发送功能，将数据包发送出去ds.send(dp);
 * 4.关闭资源
 * */
public class UDPsend {
	
	public static void main(String[] args) throws IOException {
		//1 创建UDP服务，通过DatagramSocket对象
		DatagramSocket ds = new DatagramSocket();
		//2. 确定数据，并封装成数据包。DatagramPacket(byte[] buf, int len,InetAddress add,int port);
		byte[] buf = "UDP MSG".getBytes();
		DatagramPacket dp = 
				new DatagramPacket(buf, buf.length, InetAddress.getByName("192.168.1.254"),10000);
		//3.通过socket服务，将已有的数据包发送出去
		ds.send(dp);
		//4.关闭资源
		ds.close();
	}

}
