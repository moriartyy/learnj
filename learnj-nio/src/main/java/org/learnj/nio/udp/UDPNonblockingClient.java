package org.learnj.nio.udp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

import com.google.common.base.Charsets;
import com.google.common.base.Throwables;

public class UDPNonblockingClient {

    private static final int TIMEOUT = 3000; // Resend timeout (milliseconds)
    
	private DatagramChannel channel;
	private InetSocketAddress serverAddress;
	
	public UDPNonblockingClient(String server, int port) {
		try {
	        serverAddress = new InetSocketAddress(server, port);
			channel = DatagramChannel.open();
	        channel.configureBlocking(false);
	        channel.socket().setSoTimeout(TIMEOUT);
	        channel.connect(serverAddress);
	        
		} catch (Exception e) {
			throw Throwables.propagate(e);
		}
	}
	
	public void send(String message) {
		try {
			byte[] bytesToSend = message.getBytes(Charsets.UTF_8);
	        ByteBuffer writeBuf = ByteBuffer.wrap(bytesToSend);
	        
	        channel.send(writeBuf, serverAddress);
	        
	        System.out.println("[Client] Sending message: " + message);
		} catch (Exception e) {
			throw Throwables.propagate(e);
		}
    }

	public void start() {
	}

	public void stop() {
		try {
			channel.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}