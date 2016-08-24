package org.learnj.nio.tcp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * @author hongmiao.yu on 2016/8/9.
 */
public class RegisterSockets {

    public static void main(String[] args) throws Exception {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    startServer();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        t.setDaemon(false);
        t.start();
    }

    private static void startClient() throws IOException {
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);
        Selector selector = Selector.open();
        channel.register(selector, SelectionKey.OP_CONNECT);
        channel.connect(new InetSocketAddress(9999));
        selector.select();
        Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
        while (iterator.hasNext()) {
            SelectionKey key = iterator.next();
            iterator.remove();
            if (key.isConnectable()) {
                SocketChannel channelx = (SocketChannel) key.channel();
                if (channelx.isConnectionPending()) {
                    channelx.finishConnect();
                }
                channelx.configureBlocking(false);
                ByteBuffer outBuffer = ByteBuffer.wrap("Hello, is anybody there?".getBytes());
                while (outBuffer.hasRemaining()) {
                    channel.write(outBuffer);
                }
            }
        }
        channel.close();
        selector.close();
    }

    private static void startServer() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(9999));

        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (!key.isValid()) continue;
                if (key.isAcceptable()) {
                    SocketChannel socket = ((ServerSocketChannel) key.channel()).accept();
                    socket.configureBlocking(false);
                    socket.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(64));
                } else if (key.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    int byteCount = socketChannel.read(buffer);
                    buffer.flip();
                    System.out.println(new String(buffer.array(), 0, byteCount));
                    buffer.clear();
                }
            }
        }
    }
}
