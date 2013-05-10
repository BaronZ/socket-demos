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
				//192.168.1.255这是一个广播地址，发数据到那里去，只要这个IP段的人有监听这个端口，就能收到数据。
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
		//同一ip段内，只要有人往192.168.1.255的某个端口发数据，
		//只要监听该端口，就能接到数据
		DatagramSocket receiveSocket = new DatagramSocket(10001);//
		
		new Thread(new SendThread(sendSocket)).start();
		new Thread(new ReceiveThread(receiveSocket)).start();
	}
}
