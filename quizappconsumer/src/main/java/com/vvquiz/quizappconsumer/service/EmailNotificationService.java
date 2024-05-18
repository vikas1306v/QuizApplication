package com.vvquiz.quizappconsumer.service;

import com.vvquiz.quizappconsumer.service.impl.EmailServiceImpl;
import com.vvquiz.quizappconsumer.utils.EmailDetails;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailNotificationService {

    private final EmailServiceImpl emailService;
    @KafkaListener(topics = "quiz_completed", groupId = "email-service-group")
    public void listenQuizCompleted() {
//        String event = record.value();
//        EmailDetails emailDetails = new EmailDetails();
//        emailDetails.setRecipient(
//        emailService.sendSimpleMail(event);
    }
}
