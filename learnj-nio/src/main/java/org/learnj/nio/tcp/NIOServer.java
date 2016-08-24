package org.learnj.nio.tcp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NIOServer {
	
	private Selector selector;

	public NIOServer(int port) {
		try {
			this.init(port);
		} catch (Exception e) {
		}
	}
	
	private void init(int port) throws IOException {
		ServerSocketChannel channel = ServerSocketChannel.open();
		channel.configureBlocking(false);
		print("Try binding to " + port);
		channel.bind(new InetSocketAddress(port));
		print("Open selector...");
		selector = Selector.open();
		print("Register selector...");
		channel.register(selector, SelectionKey.OP_ACCEPT);
	}
	
	public void start() {
		print("Starting server...");
		try {
			doStart();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		print("Server started.");
		print("Waiting for incoming connections...");
	}
	
	public void doStart() throws Exception {
		
		while (true) {
			selector.select();
			Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
			while (iterator.hasNext()) {
				SelectionKey key = iterator.next();
				iterator.remove();
				if (key.isAcceptable()) {
					print("Accepting a conneciton.");
					ServerSocketChannel serverChannel = (ServerSocketChannel)key.channel();
					SocketChannel clientChannel = serverChannel.accept();
					clientChannel.configureBlocking(false);
					ByteBuffer outBuffer = ByteBuffer.wrap("Welcome, What can i do for you?".getBytes());
					while (outBuffer.hasRemaining()) {
						clientChannel.write(outBuffer);
					}
					clientChannel.register(selector, SelectionKey.OP_READ);
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
					print(channel.getRemoteAddress() + message);
					ByteBuffer outBuffer = ByteBuffer.wrap("Sorry, I don't understand.".getBytes());
					while (outBuffer.hasRemaining()) {
						channel.write(outBuffer);
					}
				}
			}
		}
	}
	
	private void print(String message) {
		System.out.println("[Server]" + message);
	}
	
	public static void main(String[] args) {
		NIOServer server = new NIOServer(1234);
		server.start();
	}

}
