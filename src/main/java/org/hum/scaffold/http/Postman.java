package org.hum.scaffold.http;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import lombok.Data;

public class Postman {

	@Data
	public static class HttpRequest {
		private String host;
		private int port;
		private Socket socket;
		
		public HttpRequest(String host) {
			this(host, 80);
		}
		
		public HttpRequest(String host, int port) {
			this.host = host;
			this.port = port;
		}
		
		public String execute(String req) throws UnknownHostException, IOException {
			StringBuilder response = new StringBuilder();
			
			socket = new Socket(host, port);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"));
			bw.write(req);
			bw.flush();

			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));

			String line = null;
			while (!(line = br.readLine()).equals("")) {
				response.append(line).append("\n");
			}
			
			while ((line = br.readLine()) != null && !line.equals("")) {
				response.append(line).append("\n");
			}
			
			
			return response.toString();
		}
	}
}
