package com.baron.socket.demo;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IPDemo {

	public static void main(String[] args) throws UnknownHostException {
		
		InetAddress ia = InetAddress.getByName("www.baidu.com");
		System.out.println("address:" + ia.getHostAddress());//115.239.210.26
		System.out.println("name:" + ia.getHostName());//www.baidu.com
		System.out.println(ia.toString());//www.baidu.com/115.239.210.26
		InetAddress i = InetAddress.getLocalHost();
		System.out.println(i.getHostAddress());//192.168.1.100
		System.out.println(i.getHostName());//Baron
	}
}
