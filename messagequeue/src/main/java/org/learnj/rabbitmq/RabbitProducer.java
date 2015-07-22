package org.learnj.rabbitmq;

import java.util.UUID;

import com.rabbitmq.client.Channel;  
import com.rabbitmq.client.Connection;  
import com.rabbitmq.client.ConnectionFactory;  

public class RabbitProducer {
	
	public static void main(String[] args) throws Exception {
		
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.exchangeDeclare("shop-topic", "topic");
		
		while (true) {
			String msg = UUID.randomUUID().toString();
			channel.basicPublish("shop-topic", "shop.event.changetime", null, msg.getBytes());
			System.out.println("Sending message: " + msg);
			Thread.sleep(1000);
		}
		
//		channel.close();
//		connection.close();
	}

}
