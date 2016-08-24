package org.learnj.nio.tcp;

import java.io.ByteArrayOutputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NIOClient {
	
	private Selector selector;
	private SocketChannel channel;
	private String address;
	private int port;

	public NIOClient(String address, int port) {
		this.address = address;
		this.port = port;
		try {
			initialize();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private void initialize() throws Exception {
		channel = SocketChannel.open();
		channel.configureBlocking(false);
		selector = Selector.open();
		channel.register(selector, SelectionKey.OP_CONNECT);
	}
	
	public void start() {
		try {
			doStart();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private void doStart() throws Exception {
		channel.connect(new InetSocketAddress(address, port));
		while (true) {
			selector.select();
			Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
			while (iterator.hasNext()) {
				SelectionKey key = iterator.next();
				iterator.remove();
				if (key.isConnectable()) {
					SocketChannel channelx = (SocketChannel)key.channel();
					if (channelx == channel) {
						print("Same");
					}
					
					if (channelx.isConnectionPending()) {
						channelx.finishConnect();
					}
					channelx.configureBlocking(false);
					ByteBuffer outBuffer = ByteBuffer.wrap("Hello, is anybody there?".getBytes());
					while (outBuffer.hasRemaining()) {
						channel.write(outBuffer);
					}
					channelx.register(selector, SelectionKey.OP_READ);
				} else if (key.isReadable()) {
					print("Reading message");
					SocketChannel channel = (SocketChannel) key.channel();
					ByteBuffer buffer = ByteBuffer.allocate(10);
					ByteArrayOutputStream stream = new ByteArrayOutputStream();
					while (channel.read(buffer) > 0) {
						buffer.flip();
						stream.write(buffer.array(), 0, buffer.limit());
						buffer.clear();
					}
					String message = new String(stream.toByteArray());
					print("Received message: " + message);
					ByteBuffer outBuffer = ByteBuffer.wrap("Message received.".getBytes());
					while (outBuffer.hasRemaining()) {
						channel.write(outBuffer);
					}
					
				}
			}
		}
	}
	
	private void print(String message) {
		System.out.println("[Client]" + message);
	}
	
	public static void main(String[] args) {
		NIOClient client = new NIOClient("127.0.0.1", 1234);
		client.start();
	}
}