package spring.doodle.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQProducer {
    private final RabbitTemplate rabbitTemplate;
    private final RabbitMQConsumer rabbitMQConsumer;

    public RabbitMQProducer(RabbitMQConsumer rabbitMQConsumer, RabbitTemplate rabbitTemplate) {
        this.rabbitMQConsumer = rabbitMQConsumer;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(String message) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.topicExchange, RabbitMQConfig.stringRoutingKey, message);
//        amqpTemplate.convertAndSend(exchange, routingKey, message);
        System.out.println("Send msg = " + message);
    }

    public void send(UserDto userDto) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.topicExchange, RabbitMQConfig.objectRoutingKey, userDto);
//        amqpTemplate.convertAndSend(exchange, routingKey, message);
        System.out.println("Send msg = " + userDto.toString());
    }
}
