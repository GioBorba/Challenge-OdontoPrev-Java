package com.example.challenge_odontoprev.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue consultaQueue() {
        return new Queue("consulta-queue", false);
    }

    @Bean
    public Queue lembreteQueue() {
        return new Queue("lembrete-queue", false);
    }

    @Bean
    public Queue tratamentoQueue() {
        return new Queue("tratamento-queue", false);
    }

    @Bean
    public Queue usuarioQueue() {
        return new Queue("usuario-queue", false);
    }
}
