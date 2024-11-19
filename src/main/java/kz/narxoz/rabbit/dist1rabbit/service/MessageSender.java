package kz.narxoz.rabbit.dist1rabbit.service;

import kz.narxoz.rabbit.dist1rabbit.dto.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

@Service
@RequiredArgsConstructor
public class MessageSender {

    private final RabbitTemplate rabbitTemplate;

    @Value("${mq.message.topic.exchange}")
    private String messageTopicExchange;

    public void sendMessage(String message){
        rabbitTemplate.convertAndSend("message-exchange", "key123", message);
    }

    public void sendData(Message message, String department){
        String routingKey = "department." + department;
        rabbitTemplate.convertAndSend(messageTopicExchange, routingKey, message);
    }

}
