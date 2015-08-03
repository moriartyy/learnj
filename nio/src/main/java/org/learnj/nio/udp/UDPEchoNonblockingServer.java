package org.learnj.nio.udp;

import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.util.Iterator;

import org.learnj.common.component.AbstractLiftcycleComponent;

import com.google.common.base.Throwables;

public class UDPEchoNonblockingServer extends AbstractLiftcycleComponent {

    private static final int TIMEOUT = 3000; // Wait timeout (milliseconds)
    private volatile boolean isRunning = false;
    private Thread thread;
	private Selector selector;
	private UDPEchoSelectorProtocol echoSelectorProtocol;
    
    public UDPEchoNonblockingServer(int port) {
    	try {
    		// Create a selector to multiplex client connections.
            this.selector = Selector.open();

            DatagramChannel channel = DatagramChannel.open();
            channel.configureBlocking(false);
            channel.socket().bind(new InetSocketAddress(port));
            channel.register(selector, SelectionKey.OP_READ, new UDPEchoSelectorProtocol.ClientRecord());

            this.echoSelectorProtocol = new UDPEchoSelectorProtocol();
		} catch (Exception e) {
			throw Throwables.propagate(e);
		}
    }

    protected void run() {
        
        while (isRunning) { // Run forever, receiving and echoing datagrams
        	try {
                // Wait for task or until timeout expires
                if (selector.select(TIMEOUT) == 0) {
                    System.out.print(".");
                    continue;
                }

                // Get iterator on set of keys with I/O to process
                Iterator<SelectionKey> keyIter = selector.selectedKeys().iterator();
                while (keyIter.hasNext()) {
                    SelectionKey key = keyIter.next(); // Key is bit mask

                    // Client socket channel has pending data?
                    if (key.isReadable())
                        echoSelectorProtocol.handleRead(key);

                    // Client socket channel is available for writing and
                    // key is valid (i.e., channel not closed).
                    if (key.isValid() && key.isWritable())
                        echoSelectorProtocol.handleWrite(key);

                    keyIter.remove();
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
				UDPEchoNonblockingServer.this.run();
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
		
	}

}