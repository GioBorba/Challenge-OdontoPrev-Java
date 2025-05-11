package com.example.challenge_odontoprev.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class LembreteConsumer {

    @RabbitListener(queues = "lembrete-queue")
    public void receiveMessage(String mensagem) {
        System.out.println("[✓] Mensagem recebida da fila de lembrete: " + mensagem);
    }
}
