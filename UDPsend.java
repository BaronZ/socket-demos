package com.baron.socket.demo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/*
 * 1.����UDP socket����(ͨ��DatagramSocket����)
 * 2.�ṩ���ݣ��������ݷ�װ�����ݰ���(ͨ��DatagramPacket����)
 * 3.ͨ��socket����ķ��͹��ܣ������ݰ����ͳ�ȥds.send(dp);
 * 4.�ر���Դ
 * */
public class UDPsend {
	
	public static void main(String[] args) throws IOException {
		//1 ����UDP����ͨ��DatagramSocket����
		DatagramSocket ds = new DatagramSocket();
		//2. ȷ�����ݣ�����װ�����ݰ���DatagramPacket(byte[] buf, int len,InetAddress add,int port);
		byte[] buf = "UDP MSG".getBytes();
		DatagramPacket dp = 
				new DatagramPacket(buf, buf.length, InetAddress.getByName("192.168.1.254"),10000);
		//3.ͨ��socket���񣬽����е����ݰ����ͳ�ȥ
		ds.send(dp);
		//4.�ر���Դ
		ds.close();
	}

}
