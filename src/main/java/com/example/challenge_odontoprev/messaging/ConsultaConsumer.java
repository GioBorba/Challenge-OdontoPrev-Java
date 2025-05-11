package com.example.challenge_odontoprev.messaging;


import com.example.challenge_odontoprev.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ConsultaConsumer {

    @RabbitListener(queues = "consulta-queue")
    public void receberMensagem(String mensagem) {
        System.out.println(" [✓] Mensagem recebida da fila: " + mensagem);
    }
}
