package org.learnj.nio.udp;

public class EntryPoint {

	public static void main(String[] args) {
		
		UDPNonblockingServer server = new UDPNonblockingServer(2003);
		server.start();
		
		UDPNonblockingClient client = new UDPNonblockingClient("127.0.0.1", 2003);
		client.start();
		client.send("Hello, is anybody there?");
		client.send("Who are you?");
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		server.close();
		client.close();
	}
}
