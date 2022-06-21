package com.tez.notificationservice;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Log4j2
public class MessageListener {
    private final EmailSenderService emailSenderService;

    @RabbitListener(queues = MQConfig.CREATE_NOTIFICATION)
    public void listener(CreateNotificationEvent event) {


        log.info("Notification " + event);

        try {
            emailSenderService.sendSimpleEmail(event.getNotify().getEmail(),"Proje Eklendi: " + event.getNotify().getProject(),"Yeni Proje Eklendi");
        } catch (Exception e) {
            log.error("Cannot notify user, reason: {}", e.getMessage());
        }

    }
}
