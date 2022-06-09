package com.taskify.projectservice;

import com.taskify.projectservice.config.MQConfig;
import com.taskify.projectservice.entities.event.CreateNotificationEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Log4j2
@Component
@RequiredArgsConstructor
public class EventCreateListener {
    private final RabbitTemplate rabbitTemplate;
    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void hgsOpen(CreateNotificationEvent event) {

        rabbitTemplate.convertAndSend(MQConfig.EXCHANGE, MQConfig.CREATE_NOTIFICATION_ROUTING_KEY, event);

        log.info("Proje Bilgileri E posta adresine gönderilecektir --> "+event);


    }
}
