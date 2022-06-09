package com.keremalp.notificationservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateNotificationEvent {
    private String transactionId;
    private CreateNotificationDto notify;
    private Date date;

}
