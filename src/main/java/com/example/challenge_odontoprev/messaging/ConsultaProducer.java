package com.example.challenge_odontoprev.messaging;


import com.example.challenge_odontoprev.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultaProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final String EXCHANGE = "consulta-exchange";
    private static final String ROUTING_KEY = "consulta-routingkey";

    public void enviarMensagem(String mensagem) {
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, mensagem);
        System.out.println("Mensagem enviada para a fila: " + mensagem);
    }
}
