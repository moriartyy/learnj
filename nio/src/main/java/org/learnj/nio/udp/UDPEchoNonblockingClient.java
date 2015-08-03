package org.learnj.nio.udp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.Selector;

import org.learnj.common.component.AbstractLiftcycleComponent;

import com.google.common.base.Charsets;
import com.google.common.base.Throwables;

public class UDPEchoNonblockingClient extends AbstractLiftcycleComponent {

    private static final int TIMEOUT = 3000; // Resend timeout (milliseconds)
    private static final int MAXTRIES = 255; // Maximum retransmissions
    
    private volatile boolean isRunning = false;
    private Thread thread;
	private Selector selector;
	private UDPEchoSelectorProtocol echoSelectorProtocol;
	private String server;
	private int port;
	private DatagramChannel datagramChannel;
	
	public UDPEchoNonblockingClient(String server, int port) {
		this.server = server;
		this.port = port;
		
		try {
	        datagramChannel = DatagramChannel.open();
	        datagramChannel.configureBlocking(false);
	        datagramChannel.socket().setSoTimeout(TIMEOUT);
	        datagramChannel.connect(new InetSocketAddress("127.0.0.1", 5500));
		} catch (Exception e) {
			throw Throwables.propagate(e);
		}

	}
	
	public void send(String message) {
		try {
			byte[] bytesToSend = message.getBytes(Charsets.UTF_8);
	        ByteBuffer writeBuf = ByteBuffer.wrap(bytesToSend);
	        ByteBuffer readBuf = ByteBuffer.allocate(MAXTRIES);
	        
	        int totalBytesRcvd = 0; // Total bytes received so far
	        int bytesRcvd; // Bytes received in last read
	        while (totalBytesRcvd < bytesToSend.length) {
	            if (writeBuf.hasRemaining()) {
	                datagramChannel.write(writeBuf);
	            }
	            if ((bytesRcvd = datagramChannel.read(readBuf)) == -1) {
	                throw new SocketException("Connection closed prematurely");
	            }
	            totalBytesRcvd += bytesRcvd;
	            System.out.print("."); // Do something else
	        }

	        System.out.println("Received: " + new String(readBuf.array(), 0, totalBytesRcvd));
		} catch (Exception e) {
			throw Throwables.propagate(e);
		}
    }

	@Override
	protected void doStart() throws Exception {
	}

	@Override
	protected void doStop() throws Exception {
		datagramChannel.close();
	}

	@Override
	protected void doClose() throws Exception {
		
	}
}