package com.vvsubject.producer;

import com.vvquiz.core.SubjectCreatedEventDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class SubjectCreatedProducer {
    @Autowired
    private KafkaTemplate<String, SubjectCreatedEventDto> kafkaTemplate;
    public void sendSubjectCreatedEvent(SubjectCreatedEventDto subjectCreatedDto) {
        System.out.println("producer: " + subjectCreatedDto);
        kafkaTemplate.send("subject-create", subjectCreatedDto);
    }
}
