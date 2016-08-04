package org.learnj.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author hongmiao.yu on 2016/8/3.
 */
public class Consumer implements ExceptionListener, Runnable {

    private volatile boolean running;
    private ExecutorService executor = Executors.newSingleThreadExecutor();
    private Connection connection;
    private Session session;
    private MessageConsumer consumer;

    public void start() throws Exception {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost?create=false");
        connection = connectionFactory.createConnection(); // exception happens here...
        connection.start();
        connection.setExceptionListener(this);
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("Topic>");
        consumer = session.createConsumer(destination);
        running = true;
        executor.execute(this);
    }

    public void stop() throws Exception {
        running = false;
        executor.shutdownNow();
    }

    private void dispose() {
        try {
            consumer.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
        try {
            session.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onException(JMSException e) {
        e.printStackTrace(System.err);
    }

    @Override
    public void run() {
        while (running) {
            try {
                Message message = consumer.receive();
                if (message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message;
                    String text = textMessage.getText();
                    System.out.println("Received: " + text);
                } else {
                    System.out.println("Received obj: " + message);
                }
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
        }
        dispose();
    }
}
