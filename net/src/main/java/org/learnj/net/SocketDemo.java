package org.learnj.net;

import java.net.Socket;

public class SocketDemo {
	
	public static void main(String[] args) throws Exception {
		
		Socket socket = new Socket("192.168.1.26", 9200);
		System.out.println(socket.getReceiveBufferSize());
		System.out.println(socket.getSendBufferSize());
		socket.close();
	}

}
