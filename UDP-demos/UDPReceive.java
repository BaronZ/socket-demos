package com.baron.socket.demo;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
/*
    1������UDP socket����ͨ�������һ���˿�(DatagramSocket(int port))��
            ��������ȷ��Щ���ݹ�����Ӧ�ó���Ҫ����
����2������һ�����ݰ����洢���յ�������(DatagramPacket(byte[] data, int len))
����3��ͨ��socket�����receive���������յ������ݴ����Ѷ���õ����ݰ��С�
����4��ͨ�����ݰ���������й��ܣ�����Щ��ͬ������ȡ����
����5���ر���Դ
    note:need to run this .java first then run UDPsend.java to send data to this app.

 * 
 * */
public class UDPReceive {

	public static void main(String[] args) throws Exception {
		//1.����UDP socket,���Ѷ˿ں�10000�󶨸����Ӧ�ó���
		DatagramSocket ds = new DatagramSocket(10000);
		while(true){
			//2.�������ݰ������ڴ洢����
			byte[] buf = new byte[1024];
			DatagramPacket dp = new DatagramPacket(buf, buf.length);
			//3.ͨ�������receive���������ݴ��뵽���ݰ���
			ds.receive(dp);
			//4.ͨ�����ݰ��ķ�������ȡ���е����ݡ�
			String ip = dp.getAddress().getHostAddress();//���Ͷ˵�IP��ַ
			String data = new String(dp.getData(), 0, dp.getLength());
			int port = dp.getPort();//���Ͷ˵Ķ˿ں�
			System.out.println("received data");
			System.out.println(ip + "::" + data + "::" + port);
			//5.�ر���Դ
			//ds.close();
		}
	}
}
