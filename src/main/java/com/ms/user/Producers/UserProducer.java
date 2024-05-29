package com.ms.user.Producers;

import com.ms.user.dtos.EmailDto;
import com.ms.user.models.User;
import jakarta.validation.constraints.Email;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserProducer {

    final RabbitTemplate rabbitTemplate;

    public UserProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value(value = "${broker.queue.email.name}")
    private String routingKey;

    public void publishMessageEmail(User user){
        var emailDto = new EmailDto();
        emailDto.setEmailTo(user.getEmail());
        emailDto.setUserId(user.getId());
        emailDto.setSubject("Hello, it's a test email");
        emailDto.setText(user.getName() + "Test\n\n A BIG TEST\n");

        rabbitTemplate.convertAndSend("", routingKey, emailDto);
    }
}
