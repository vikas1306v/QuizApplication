package com.vvquiz.quizappconsumer.consumer;

import com.vvquiz.core.SubjectCreatedEventDto;
import com.vvquiz.core.SubjectUpdatedEventDto;
import com.vvquiz.quizappconsumer.service.impl.EmailServiceImpl;
import com.vvquiz.quizappconsumer.utils.EmailDetails;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailNotificationService {

    private final EmailServiceImpl emailService;
    @KafkaListener(topics = "subject-create", groupId = "email-service-group",containerFactory = "subjectCreatedEventKafkaListenerContainerFactory")
    public void listenSubjectCreated(SubjectCreatedEventDto subjectCreatedDto) {
        EmailDetails details=new EmailDetails();
        details.setRecipient(subjectCreatedDto.getEmail());
        details.setSubject("subject created successfully");
        details.setMsgBody(subjectCreatedDto.getCode());
        emailService.sendSimpleMail(details);
    }
    @KafkaListener(topics = "subject-updated-topic", groupId = "email-service-group", containerFactory = "subjectUpdatedEventKafkaListenerContainerFactory")
    public void listenToSubjectUpdatedEvent(SubjectUpdatedEventDto event) {
        System.out.println("Received SubjectUpdatedEvent: " + event);
    }
}
