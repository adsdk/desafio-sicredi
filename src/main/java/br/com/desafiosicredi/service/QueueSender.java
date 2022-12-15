package br.com.desafiosicredi.service;

import br.com.desafiosicredi.config.QueueConfig;
import br.com.desafiosicredi.utils.JsonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QueueSender {

    private final RabbitTemplate rabbitTemplate;
    private final QueueConfig queueConfig;
    private final JsonUtils jsonUtils;

    public void sendConsolidacao(Object obj) {
        rabbitTemplate.convertAndSend(this.queueConfig.getConsolidacao(), jsonUtils.toJson(obj));
    }

}
