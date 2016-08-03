package org.learnj.rabbitmq;

import java.io.IOException;
import java.util.concurrent.Executors;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;  
import com.rabbitmq.client.Connection;  
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class RabbitConsumer {
	
	//

	public static void main(String[] args) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(RabbitmqConf.serverHost);
		factory.setPort(RabbitmqConf.serverPort);
		factory.setUsername(RabbitmqConf.userName);
		factory.setPassword(RabbitmqConf.password);
		factory.setSharedExecutor(Executors.newSingleThreadExecutor());
		System.out.println("Factory created.");
		
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		System.out.println("Channel created.");
		
		String quque = RabbitmqConf.topicQueue;
		try {
			channel.queueDeclare(quque, true, false, false, null);
			channel.queueBind(quque, RabbitmqConf.topicExchange, "book.#");
		} catch (Exception e) {
			System.out.println(e);
		}
        
        boolean autoAck = true;
        System.out.println("Waiting for incoming message...");
        channel.basicConsume(quque, autoAck, "test_consumer1",
             new DefaultConsumer(channel) {
                 @Override
                 public void handleDelivery(String consumerTag,
                                            Envelope envelope,
                                            AMQP.BasicProperties properties,
                                            byte[] body)
                     throws IOException
                 {
                     String routingKey = envelope.getRoutingKey();
                     long deliveryTag = envelope.getDeliveryTag();
                     System.out.println("Received: " + routingKey + "#" + deliveryTag);
                     System.out.println("Thread: " + Thread.currentThread().getId());
                     System.out.println("---------------------------------------------------");
                     String message = new String(body);
                     System.out.println(message);
                     System.out.println("---------------------------------------------------");
                     System.out.println();
                     System.out.println();
//                     channel.basicAck(deliveryTag, false);
                     try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
                 }
             }); 
	}
}
