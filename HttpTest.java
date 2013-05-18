package com.baron.socket.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class HttpTest {

	public static void main(String[] args) throws UnknownHostException, IOException {
//		Socket s = new Socket("127.0.0.1", 8080);//connect tomcat
		Socket s = new Socket("www.baidu.com", 80);
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
		//send request to tomcat
		//http header
		pw.println("GET / HTTP/1.1");
//		pw.println("Host: localhost");
		pw.println("Host: www.baidu.com");
		pw.println("Content-Type: text/html;charset=utf-8");
	
		pw.println();
		pw.flush();
		//get response data from tomcat server
		BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		String str = "";
		while((str = br.readLine()) != null){
			System.out.println(str);
		}
		br.close();
		pw.close();
		s.close();
	}
}
/*
 * output:
 *  http reponse header + html code like below
 *  
 *  HTTP/1.1 200 OK
	Server: Apache-Coyote/1.1
	Accept-Ranges: bytes
	ETag: W/"7446-1367206570000"
	Last-Modified: Mon, 29 Apr 2013 03:36:10 GMT
	Content-Type: text/html
	Content-Length: 7446
	Date: Sat, 18 May 2013 11:48:55 GMT
 * <html>
 * 
 * 		html/css code omitted
 *  
 * </html>
 * 
 * 
 * */
 