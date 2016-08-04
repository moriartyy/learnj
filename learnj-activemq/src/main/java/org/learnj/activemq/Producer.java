package org.learnj.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author hongmiao.yu on 2016/8/3.
 */
public class Producer implements ExceptionListener {

    private Connection connection;
    private Session session;
    private MessageProducer producer;

    public void start() throws Exception {

        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost?create=false"); // apparently the vm part is all i need
        connection = connectionFactory.createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("Topic>");
        producer = session.createProducer(destination);
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
    }

    public void stop() throws Exception {
        session.close();
        connection.close();
        producer.close();
    }

    public void send(String message) throws JMSException {
        TextMessage msg = session.createTextMessage(message);
        System.out.println("Sent: " + message);
        producer.send(msg);
    }

    @Override
    public void onException(JMSException e) {
        e.printStackTrace(System.err);
    }
}
