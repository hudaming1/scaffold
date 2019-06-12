package org.hum.scaffold.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpSimpleServer {

	static class Config {
		public static final int PORT = 3389;
	}
	
	public static void main(String[] args) throws InterruptedException {
		try {
			@SuppressWarnings("resource")
			ServerSocket server = new ServerSocket(Config.PORT);
			System.out.println("server started, listenning on " + Config.PORT);
			while (true) {
				Socket socket = server.accept();
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				OutputStream outputStream = socket.getOutputStream();
				String requestHeader;

				String requestLine = br.readLine();
				
				System.out.println("request line:" + requestLine);
				System.out.println("request header:");
				while (!(requestHeader = br.readLine()).equals("")) {
					System.out.println(requestHeader);
				}
				System.out.println("=========================================");

				if (requestLine.startsWith("CONNECT")) {
					// 将已联通信号返回给请求页面(https才会有这么一步)
					outputStream.write("HTTP/1.1 200 Connection established\r\n\r\n".getBytes());
					outputStream.flush();
					System.out.println("flush Connection established");
				}

				PrintWriter pw = new PrintWriter(socket.getOutputStream());
				pw.println("HTTP/1.1 200 OK");
				pw.println("Content-type:text/html");
				pw.println(); 
				pw.println("<h1>Hello HttpServer</h1>");
				pw.flush();
				System.out.println("flush response");
				
				socket.close();
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
