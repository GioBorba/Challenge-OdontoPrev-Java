package com.example.challenge_odontoprev.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class TratamentoProducer {

    private final RabbitTemplate rabbitTemplate;

    public TratamentoProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String mensagem) {
        rabbitTemplate.convertAndSend("tratamento-queue", mensagem);
    }
}
