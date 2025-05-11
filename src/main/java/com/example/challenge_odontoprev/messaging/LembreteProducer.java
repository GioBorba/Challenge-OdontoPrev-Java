package com.example.challenge_odontoprev.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class LembreteProducer {

    private final RabbitTemplate rabbitTemplate;

    public LembreteProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String mensagem) {
        rabbitTemplate.convertAndSend("lembrete-queue", mensagem);
    }
}
