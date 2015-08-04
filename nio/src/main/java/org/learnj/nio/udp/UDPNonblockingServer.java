package org.learnj.nio.udp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;

import org.learnj.common.component.AbstractLiftcycleComponent;

import com.google.common.base.Charsets;
import com.google.common.base.Throwables;

public class UDPNonblockingServer extends AbstractLiftcycleComponent {

    private static final int TIMEOUT = 3000; // Wait timeout (milliseconds)
    private volatile boolean isRunning = false;
    private Thread thread;
	private Selector selector;
    
    public UDPNonblockingServer(int port) {
    	try {
            this.selector = Selector.open();

            DatagramChannel channel = DatagramChannel.open();
            channel.configureBlocking(false);
            channel.socket().bind(new InetSocketAddress(port));
            channel.register(selector, SelectionKey.OP_READ);

		} catch (Exception e) {
			throw Throwables.propagate(e);
		}
    }
    
	public String readMessage(SelectionKey key) throws IOException {
		ByteBuffer byteBuffer = ByteBuffer.allocate(65536);
		DatagramChannel channel = (DatagramChannel) key.channel();
		// Under udp you can not use 'read'.
		SocketAddress remote = channel.receive(byteBuffer);
		if (remote != null) {
			byteBuffer.flip();
			CharBuffer charBuffer = Charset.defaultCharset().decode(byteBuffer);
			String message = charBuffer.toString();
			
			System.out.println("[Server] Message received from " + remote + ": " + message);
			return message;
		}
		return null;
	}
	
	public void writeMessage(SelectionKey key, String message) throws IOException {
		DatagramChannel channel = (DatagramChannel) key.channel();
		byte[] bytesToSend = message.getBytes(Charsets.UTF_8);
        ByteBuffer writeBuf = ByteBuffer.wrap(bytesToSend);
        
        while (writeBuf.hasRemaining()) {
        	channel.write(writeBuf);
        }
        System.out.println("[Server] Sending message: " + message);
	}

    protected void run() {
        
        while (isRunning) { 
        	try {
        		System.out.println("[Server] Waiting for incoming message...");
                if (selector.select(TIMEOUT) == 0) {
                    System.out.print(".");
                    continue;
                }

                Iterator<SelectionKey> keyIter = selector.selectedKeys().iterator();
                while (keyIter.hasNext()) {
                    SelectionKey key = keyIter.next(); 
                    String message;
                    if (key.isReadable()) {
                    	message = readMessage(key);
//                    	key.interestOps(SelectionKey.OP_WRITE);
//                        if (key.isValid() && key.isWritable()) {
//                        	// Send message back to client.
//                        	writeMessage(key, message);
//                        	key.interestOps(SelectionKey.OP_READ);
//                        }
                        keyIter.remove();
                    }
                }
        	} catch (Exception e) {
        		isRunning = false;
        		throw Throwables.propagate(e);
        	}
        }
    }

	@Override
	protected void doStart() throws Exception {
		isRunning = true;
		thread = new Thread() {
			
			@Override
			public void run() {
				UDPNonblockingServer.this.run();
			}
		};
		thread.start();
	}

	@Override
	protected void doStop() throws Exception {
		isRunning = false;
		while (thread.isAlive()) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
			}
		}
	}

	@Override
	protected void doClose() throws Exception {
		doStop();
	}

}