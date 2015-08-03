package org.learnj.rabbitmq;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.concurrent.Executors;

import org.learnj.common.text.Strings;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
		factory.setHost("192.168.1.16");
		factory.setUsername("admin");
		factory.setPassword("admin");
		factory.setSharedExecutor(Executors.newSingleThreadExecutor());
		System.out.println("Factory created.");
		
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		System.out.println("Channel created.");
		
		String quque = "sku-sync-test";
		try {
			channel.queueDeclare(quque, true, false, false, null);
			channel.queueBind(quque, "exchange_search_v1", "sku.#");
		} catch (Exception e) {
			System.out.println(e);
		}
        
        boolean autoAck = false;
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
                     String asc = new String(body, "US-ASCII");
                     System.out.println(asc);
                     System.out.println("---------------------------------------------------");
                     Map<String, String> map = new Gson().fromJson(asc, new TypeToken<Map<String, String>>() {}.getType());
                     System.out.println(map.get("title"));
                     System.out.println("---------------------------------------------------");
                     System.out.println();
                     System.out.println();
//                     channel.basicAck(deliveryTag, false);
//                     channel.basicReject(deliveryTag, true);
                     try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
                 }
             }); 
	}
}
