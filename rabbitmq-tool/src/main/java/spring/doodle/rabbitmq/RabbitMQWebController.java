package spring.doodle.rabbitmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/javainuse-rabbitmq/")
public class RabbitMQWebController {
    @Autowired
    RabbitMQProducer rabbitMQProducer;

    @GetMapping(value = "/producerString/{message}")
    public String producer(@PathVariable("message") String message) {
        rabbitMQProducer.send(message);
        return "Message sent to the RabbitMQ JavaInUse Successfully";
    }

    @GetMapping(value = "/producerObject/{name}")
    public String producerObject(@PathVariable("name") String name) {
        UserDto u = new UserDto(1, name);
        rabbitMQProducer.send(u);
        return "Message sent to the RabbitMQ JavaInUse Successfully";
    }
}
