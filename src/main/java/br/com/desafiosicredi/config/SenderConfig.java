package br.com.desafiosicredi.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SenderConfig {

    private final QueueConfig queueConfig;

    @Bean
    public Queue queue() {
        return new Queue(queueConfig.getConsolidacao(), true);
    }
}
