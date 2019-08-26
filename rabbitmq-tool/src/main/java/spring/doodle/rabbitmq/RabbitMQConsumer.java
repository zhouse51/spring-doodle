package spring.doodle.rabbitmq;

import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class RabbitMQConsumer {
//    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveStringMessage(String message) {
        System.out.println("Received <" + message + ">");
//        latch.countDown();
//        System.out.println("Latch: " + latch.getCount());
    }

    public void receiveObjectMessage(UserDto user) {
        System.out.println("Received <" + user.toString() + ">");
//        latch.countDown();
//        System.out.println("Latch: " + latch.getCount());
    }

//    public CountDownLatch getLatch() {
//        return latch;
//    }
}