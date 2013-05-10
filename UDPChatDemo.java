package com.baron.socket.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;


class SendThread implements Runnable{
	
	private DatagramSocket ds;
	public SendThread(DatagramSocket ds){
		this.ds = ds;
	}
	
	@Override
	public void run() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		try {
			while((line = br.readLine()) != null){
				if(line.equals("bye")){
					break;
				}
				byte[] buf = line.getBytes();
				//192.168.1.255����һ���㲥��ַ�������ݵ�����ȥ��ֻҪ���IP�ε����м�������˿ڣ������յ����ݡ�
				DatagramPacket dp = new DatagramPacket(buf, buf.length, InetAddress.getByName("192.168.1.255"), 10001);
				
				ds.send(dp);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

class ReceiveThread implements Runnable{

	private DatagramSocket ds;
	public ReceiveThread(DatagramSocket ds){
		this.ds = ds;
	}
	@Override
	public void run() {
		while(true){ 
			byte[] buf = new byte[1024];
			DatagramPacket dp = new DatagramPacket(buf, buf.length);
			try {
				ds.receive(dp);
				
				String ip = dp.getAddress().getHostAddress();
				String data = new String(dp.getData(), 0, dp.getLength());
				System.out.println(ip + "::" + data);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}

public class UDPChatDemo {

	public static void main(String[] args) throws SocketException {
		DatagramSocket sendSocket = new DatagramSocket();
		//ͬһip���ڣ�ֻҪ������192.168.1.255��ĳ���˿ڷ����ݣ�
		//ֻҪ�����ö˿ڣ����ܽӵ�����
		DatagramSocket receiveSocket = new DatagramSocket(10001);//
		
		new Thread(new SendThread(sendSocket)).start();
		new Thread(new ReceiveThread(receiveSocket)).start();
	}
}
