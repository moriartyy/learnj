package org.learnj.rabbitmq;

import java.io.IOException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;  
import com.rabbitmq.client.Connection;  
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class RabbitConsumer {

	public static void main(String[] args) throws Exception {
		
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		try {
			channel.queueDeclare("ShopMessageConsumerOfSearch", true, false, false, null);
			channel.queueBind("ShopMessageConsumerOfSearch", "shop-topic", "shop.event.changetime");
		} catch (Exception e) {
			System.out.println(e);
		}
        
        boolean autoAck = false;
        channel.basicConsume("ShopMessageConsumerOfSearch", autoAck, "myConsumerTag",
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
                     System.out.println("Received: " + new String(body));
                     channel.basicAck(deliveryTag, false);
                 }
             }); 
	}
}
