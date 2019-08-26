package spring.doodle.rabbitmq;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    static final String topicExchange = "topic-exchange";

    static final String stringQueueName = "string-queue";

    static final String objectQueueName = "object-queue";

    static final String stringRoutingKey = "string.routing.#";

    static final String objectRoutingKey = "object.routing.#";

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(topicExchange);
    }

    @Bean
    Queue stringQueue() {
        return new Queue(stringQueueName, false);
    }

    @Bean
    Queue objectQueue() {
        return new Queue(objectQueueName, false);
    }

    @Bean
    Binding stringBinding(Queue stringQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(stringQueue).to(topicExchange).with(stringRoutingKey);
    }

    @Bean
    Binding objectBinding(Queue objectQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(objectQueue).to(topicExchange).with(objectRoutingKey);
    }

    @Bean
    SimpleMessageListenerContainer containerString(ConnectionFactory connectionFactory, MessageListenerAdapter stringListenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(stringQueueName);
        container.setMessageListener(stringListenerAdapter);
        return container;
    }

    @Bean
    SimpleMessageListenerContainer containerObject(ConnectionFactory connectionFactory, MessageListenerAdapter objectListenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(objectQueueName);
        container.setMessageListener(objectListenerAdapter);
        return container;
    }

    @Bean
    MessageListenerAdapter stringListenerAdapter(RabbitMQConsumer rabbitMQConsumer) {
        return new MessageListenerAdapter(rabbitMQConsumer, "receiveStringMessage");
    }

    @Bean
    MessageListenerAdapter objectListenerAdapter(RabbitMQConsumer rabbitMQConsumer) {
        return new MessageListenerAdapter(rabbitMQConsumer, "receiveObjectMessage");
    }
}
